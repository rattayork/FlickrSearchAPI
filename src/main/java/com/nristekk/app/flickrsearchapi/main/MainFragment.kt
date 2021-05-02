package com.nristekk.app.flickrsearchapi.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.flatMap
import androidx.recyclerview.widget.DividerItemDecoration
import com.nristekk.app.flickrsearchapi.FlickrSearchApp
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.databinding.FragmentMainBinding
import com.nristekk.app.flickrsearchapi.history.HistoryViewModel
import com.nristekk.app.flickrsearchapi.history.keywordcache.KeyWordSharedPref
import com.nristekk.app.flickrsearchapi.main.ui.PhotoAdapter
import com.nristekk.app.flickrsearchapi.main.ui.PhotoLoadStateAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var keyWordSharedPref: KeyWordSharedPref

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val serviceVM by viewModels<ServiceViewModel> { viewModelFactory }
    private val historyVM by viewModels<HistoryViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentMainBinding
    private lateinit var adapter: PhotoAdapter


    //Inject this
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FlickrSearchApp).appComponent.mainComponent().create()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Inflate the View using ViewDataBinding
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            serviceViewModel = serviceVM
        }

        //We show options menu here
        setHasOptionsMenu(true)
        (activity as MainActivity).showMenuHistory()

        //Return DataBinding's root View
        return viewDataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewDataBinding.list.addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))

        setupInputTextView()

        setupAdapter()

        setupSwipeRefresh()

        doSearch()

    }


    /*
    * Main function to present outcome to the Users
    */
    private fun setupAdapter(){
        val picasso = Picasso.with(requireContext())
        adapter = PhotoAdapter(picasso)
        viewDataBinding.list.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter(adapter),
                footer = PhotoLoadStateAdapter(adapter)
        )

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadStates ->
                viewDataBinding.mainRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            serviceVM.flow.collectLatest {
                adapter.submitData(it)
            }
        }


    }

    /*
    * Main operation function to launch searching
    */
    private fun doSearch(){
        viewDataBinding.keywordInput.text.trim().let {
            if (it.isNotEmpty()) {
                viewDataBinding.list.scrollToPosition(0)

                val keyWord = it.toString()

                //Let's it run
                serviceVM.doSearch(keyWord)

                //Let's record input into History as well
                historyVM.insert(History(keyWord))

            }else{
                Toast.makeText(requireContext(), getText(R.string.warn_input_empty), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Prepare InputView's autoComplete, Listening the state and so on...
    private fun setupInputTextView(){

        //Get cached keyword from Activity
        viewDataBinding.keywordInput.setText(keyWordSharedPref.getKeywordCache())


        //set adapter for autoComplete purpose
        historyVM.getListLive().observe(viewLifecycleOwner, Observer {
            val keywordList = mutableListOf<String>()
            it.forEach {
                keywordList.add(it.keyword)
            }
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, keywordList)
           viewDataBinding.keywordInput.setAdapter(adapter)
        })

        //Listen if IME was pressed
        viewDataBinding.keywordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                doSearch()
                true
            } else {
                false
            }
        }

        //Listen if 'Enter' was press
        viewDataBinding.keywordInput.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch()
                true
            } else {
                false
            }
        }
    }

    private fun setupSwipeRefresh(){
        viewDataBinding.mainRefreshLayout.setOnRefreshListener { adapter.refresh() }
    }



}