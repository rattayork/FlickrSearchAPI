package com.nristekk.app.flickrsearchapi.data

import android.util.Log
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.data.entity.HistoryDao

/*
* Method being used for do pre-populate database using CoroutineScope
*/
suspend fun populateDatabaseScope(historyDao: HistoryDao){
    historyDao.insert(History("Hello"))
    historyDao.insert(History("World!"))
}
