package com.nristekk.app.flickrsearchapi.history

import android.util.Log
import androidx.lifecycle.*
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.data.Event
import com.nristekk.app.flickrsearchapi.data.Result
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.data.repos.HistoryRepos
import com.nristekk.app.flickrsearchapi.history.keywordcache.KeyWordSharedPref
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
private val historyRepos: HistoryRepos,
private val keyWordSharedPref: KeyWordSharedPref
): ViewModel() {

    private val _items = MutableLiveData<List<History>>().apply { value = emptyList() }
    val items: LiveData<List<History>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastMsg = MutableLiveData<Event<Int>>()
    val toastMsg : LiveData<Event<Int>> = _toastMsg

    private val _openFromHistory = MutableLiveData<Event<Unit>>()
    val openFromHistory: LiveData<Event<Unit>> = _openFromHistory


    init {
        // Set initial state
        loadHistories()
    }


    //Main task to load List of History
    fun loadHistories(){
        //In real App it should has cache to store loaded history
        _dataLoading.value = true
        viewModelScope.launch {

            val loadResult = historyRepos.getList()

            if(loadResult is Result.Success){
                val historyList = loadResult.data
                _items.value = historyList
            }else{
                //result is not success, tell user something
                showToastMessage(R.string.pls_comeback_later)
            }
            _dataLoading.value = false
        }

    }

    fun refresh() {
        loadHistories()
    }

    //This will let Fragment be able to observe some Event to display message to the user
    private fun showToastMessage(message: Int) {
        _toastMsg.value = Event(message)
    }


    // Transform Flow into LiveData. just in case we need it to be simply observable
    fun getListLive(): LiveData<List<History>>{
        return historyRepos.getListFlow().asLiveData()
    }

    fun insert(history: History){
        viewModelScope.launch {
            historyRepos.insert(history)
        }
    }

    fun delete(history: History){
        viewModelScope.launch {
            historyRepos.delete(history)
            refresh()
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            historyRepos.deleteAll()
            refresh()
            showToastMessage(R.string.all_history_cleared)
        }
    }

    fun openKeyword(keyword:String){
        _openFromHistory.value = Event(Unit)
        keyWordSharedPref.setKeywordCache(keyword)
    }


}