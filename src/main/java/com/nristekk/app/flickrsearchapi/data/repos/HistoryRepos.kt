package com.nristekk.app.flickrsearchapi.data.repos

import androidx.lifecycle.LiveData
import com.nristekk.app.flickrsearchapi.data.Result
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.data.entity.HistoryDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HistoryRepos @Inject constructor(
        private val historyDao: HistoryDao
        ,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):HistoryImpl{


    override fun getListFlow(): Flow<List<History>> {
        return historyDao.getListFlow()
    }

    //Get List as scoped way try to wrapper with Result to hold loading state
    override suspend fun getList(): Result<List<History>> = withContext(ioDispatcher) {
        //Delay a bit here to at least show refreshing animation
        delay(500)
        return@withContext try{
            Result.Success(historyDao.getList())
        }catch (e:Exception){
            Result.Error(e)
        }
    }

    override suspend fun insert(history: History) {
        coroutineScope {
            launch { historyDao.insert(history) }
        }
    }

    override suspend fun delete(history: History) {
        coroutineScope {
            launch { historyDao.delete(history) }
        }
    }

    override suspend fun deleteAll() {
        coroutineScope {
            launch { historyDao.deleteAll() }
        }

    }


}