package com.nristekk.app.flickrsearchapi.history.keywordcache

import android.content.Context
import com.nristekk.app.flickrsearchapi.data.MyDataUtil
import javax.inject.Inject



class KeyWordSharedPref @Inject constructor(context: Context) : KeyWordSharedPrefImpl {

    private val sharedPreferences = context.getSharedPreferences("FlickrSearchAPI", Context.MODE_PRIVATE)


    override fun setKeywordCache(queryKeyword: String) {
        with(sharedPreferences.edit()){
            putString(MyDataUtil.KEY_QUERY, queryKeyword)
            apply()
        }

    }

    override fun getKeywordCache(): String {
        return sharedPreferences.getString(MyDataUtil.KEY_QUERY, "")!!

    }
}
