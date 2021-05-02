package com.nristekk.app.flickrsearchapi.di

import com.nristekk.app.flickrsearchapi.data.repos.HistoryImpl
import com.nristekk.app.flickrsearchapi.data.repos.HistoryRepos
import com.nristekk.app.flickrsearchapi.history.keywordcache.KeyWordSharedPref
import com.nristekk.app.flickrsearchapi.history.keywordcache.KeyWordSharedPrefImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindHistoryRepos(historyRepos: HistoryRepos):HistoryImpl


    // Makes Dagger provide KeywordCached using SharedPreferences
    @Singleton
    @Binds
    abstract fun bindKeywordCache(keyWordSharedPref: KeyWordSharedPref): KeyWordSharedPrefImpl

}