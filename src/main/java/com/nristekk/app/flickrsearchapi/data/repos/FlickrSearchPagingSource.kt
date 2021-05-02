package com.nristekk.app.flickrsearchapi.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nristekk.app.flickrsearchapi.api.FlickrSearchApi
import com.nristekk.app.flickrsearchapi.data.Result
import com.nristekk.app.flickrsearchapi.data.entity.FlickrPhotoItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/*
* PagingSource class
* it took Search Api and keyword to be query as parameters
 */

class FlickrSearchPagingSource @Inject constructor(
 val flickrSearchApi: FlickrSearchApi,
 val query:String
) : PagingSource<Int, FlickrPhotoItem>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrPhotoItem> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1

            val response = flickrSearchApi.get(
                   text = query,
                   page = nextPageNumber
            ).data
            val items = response.photos
            LoadResult.Page(
                    data = items,
                    prevKey = null, // Only paging forward.
                    nextKey = response.page+1  /*next page to load is just reponse + 1*/
            )
        }catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FlickrPhotoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}

