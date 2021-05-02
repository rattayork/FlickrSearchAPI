package com.nristekk.app.flickrsearchapi.main


import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nristekk.app.flickrsearchapi.api.FlickrSearchApi
import com.nristekk.app.flickrsearchapi.data.MyDataUtil
import com.nristekk.app.flickrsearchapi.data.entity.FlickrPhotoItem
import com.nristekk.app.flickrsearchapi.data.repos.FlickrSearchPagingSource
import com.nristekk.app.flickrsearchapi.data.repos.HistoryRepos
import com.nristekk.app.flickrsearchapi.history.keywordcache.KeyWordSharedPref
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
* Service ViewModel for app's main purpose is
* to get Data (Node object) from service provider or endPoint of Flickr
*/
class ServiceViewModel @Inject constructor(
        private val flickrSearchApi: FlickrSearchApi,
        private val keyWordSharedPref: KeyWordSharedPref
): ViewModel()
{

    //Store keyword as LiveData
    private val queryLiveData = MutableLiveData<String>()

    //Channel coroutine to store and to empty flow
    private val clearListCh = Channel<Unit>(Channel.CONFLATED)


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val flow = flowOf(
            clearListCh.receiveAsFlow().map { PagingData.empty<FlickrPhotoItem>() },
            queryLiveData.asFlow()
                    //Flatmap empty PagingData to gether with query keyword as input of PagingSource
                    .flatMapLatest {
                        //Do load Page
                        getFlowFromPagingSource(it)
                    }.cachedIn(viewModelScope)
            ).flattenMerge(2)


    //Modular function to get Flow from Paging Source
    fun getFlowFromPagingSource(query:String) = Pager(
        PagingConfig(
            pageSize = MyDataUtil.MY_PAGE_SIZE_1,
            //enablePlaceholders = false
        )
        ){ FlickrSearchPagingSource(
                flickrSearchApi = flickrSearchApi,
                query = query
            )
        }.flow


    //Main operation function to get PageSource and ServiceApi to launched
    fun doSearch(keyword:String){

       clearListCh.offer(Unit)

       //transform 'keyword' to be reactive
       queryLiveData.postValue(keyword)

       //record 'keyword' into cached
       keyWordSharedPref.setKeywordCache(keyword)

    }


}