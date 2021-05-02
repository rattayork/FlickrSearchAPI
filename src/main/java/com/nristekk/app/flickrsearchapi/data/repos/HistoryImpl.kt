package com.nristekk.app.flickrsearchapi.data.repos

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.nristekk.app.flickrsearchapi.data.Result
import com.nristekk.app.flickrsearchapi.data.entity.History
import kotlinx.coroutines.flow.Flow


interface HistoryImpl {

    // Transform into LiveData. just in case we need it to be simply observable
    fun getListFlow(): Flow<List<History>>

    //Get List, using scoped way
    suspend fun getList(): Result<List<History>>

    suspend fun insert(history:History)

    suspend fun delete(history: History)

    suspend fun deleteAll()

}