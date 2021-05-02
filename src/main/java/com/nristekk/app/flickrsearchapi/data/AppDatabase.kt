package com.nristekk.app.flickrsearchapi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.data.entity.HistoryDao


/**
 * The Room Database that contains the Task table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao



}
