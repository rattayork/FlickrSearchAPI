package com.nristekk.app.flickrsearchapi.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nristekk.app.flickrsearchapi.FlickrSearchApp
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.data.EventObserver
import com.nristekk.app.flickrsearchapi.databinding.FragmentHistoryBinding
import com.nristekk.app.flickrsearchapi.main.MainActivity
import javax.inject.Inject

class HistoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentHistoryBinding
    private lateinit var listAdapter: HistoryAdapter

    //Inject this
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FlickrSearchApp).appComponent.historyComponent().create()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Inflate the View using ViewDataBinding
        viewDataBinding = FragmentHistoryBinding.inflate(inflater, container, false).apply {
            historyViewModel = viewModel
        }

        //We won't show options menu here
        setHasOptionsMenu(false)
        (activity as MainActivity).hideMenuHistory()

        //Return DataBinding's root View
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        //decorate some
        viewDataBinding.historyList.addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))

        //observe if there is any message from ViewModel
        viewModel.toastMsg.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), getString(it), Toast.LENGTH_LONG).show()
        })

        //observe if user click keyword from History
        viewModel.openFromHistory.observe(viewLifecycleOwner, EventObserver{
            (activity as MainActivity).keywordFromHistoryClicked()
        })

        setupListAdapter()
        setupDeleteAllButton()

    }

    //crucial function to bind adapter, view with ViewModel list
    private fun setupListAdapter() {
        val viewModel = viewDataBinding.historyViewModel
        if (viewModel != null) {
            listAdapter = HistoryAdapter(viewModel)
            viewDataBinding.historyList.adapter = listAdapter
        } else {
            Log.d(getString(R.string.logged),"There were something wrong, establish ViewModel to be bind.")
        }
    }

    private fun setupDeleteAllButton(){
        viewDataBinding.trashButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
            builder.setMessage("Clear all history?")
            builder.setPositiveButton(getString(R.string.my_yes)) { dialog, which -> viewModel.deleteAll() }
            builder.setNegativeButton(getString(R.string.my_no)){ dialog, which -> {/*do nothing*/}}
            builder.show()
        }
    }


}