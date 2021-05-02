package com.nristekk.app.flickrsearchapi.api

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.nristekk.app.flickrsearchapi.data.entity.FlickrPhotoItem
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
and instances will be used to get result from Service Provider Endnode
 */
interface FlickrSearchApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=37ad288835e4c64fc0cb8af3f3a1a65d&format=json&nojsoncallback=1&safe_search=1")
    suspend fun get(
            @Query("text") text: String,
            @Query("page") page: Int
    ): ListingResponse

    //Wrapper calss reflect 'photos' deSerialized
    class ListingResponse(
            @SerializedName("photos") val data:ListingData,
            @SerializedName("stat") val stat: String?
    )
    //Class to hold Reply from EndNode in Listing style
    data class ListingData(
            @SerializedName("page") val page: Int = 0,
            @SerializedName("pages") val pages: Int = 0,
            @SerializedName("perpage") val ItemPerPage: Int = 0,
            @SerializedName("total") val total: Int = 0,
            @SerializedName("photo") val photos: List<FlickrPhotoItem> = emptyList(),
            val nextPage: Int?
    )

    companion object {
        private const val BASE_URL = "https://api.flickr.com/"
        fun create(): FlickrSearchApi{
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FlickrSearchApi::class.java)
        }
    }

}