package com.nristekk.app.flickrsearchapi.history.keywordcache



interface KeyWordSharedPrefImpl {
    fun setKeywordCache(queryKeyword: String)
    fun getKeywordCache(): String
}