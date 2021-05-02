package com.nristekk.app.flickrsearchapi.data.entity

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


/*
Data access object for History keyword
 */

@Dao
interface HistoryDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    // Transform into LiveData. just in case we need it to be simply observable
    @Query("SELECT * FROM history ORDER BY keyword ASC")
    fun getListFlow(): Flow<List<History>>

    // get List from scoped fashion
    @Query("SELECT * FROM history ORDER BY keyword ASC")
    suspend fun getList(): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(history: History)

    //Delete specific item
    @Delete
    suspend fun delete(history: History)

    @Query("DELETE FROM history")
    suspend fun deleteAll()

}