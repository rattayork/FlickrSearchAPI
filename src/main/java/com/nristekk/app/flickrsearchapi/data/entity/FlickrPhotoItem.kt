package com.nristekk.app.flickrsearchapi.data.entity

import com.google.gson.annotations.SerializedName


/*
We may need Paging's Mediator in the future, so we define
flicker response model here in advance, in case we need to
record them into Db in the future
 */
data class FlickrPhotoItem(
@field:SerializedName("id") val id: Long,
@field:SerializedName("owner") val owner: String,
@field:SerializedName("secret") val secret: String,
@field:SerializedName("server") val server: String,
@field:SerializedName("farm") val farm: String,
@field:SerializedName("title") val title: String,
@field:SerializedName("ispublic") val ispublic: Int,
@field:SerializedName("isfriend") val isfriend: Int,
@field:SerializedName("isfamily") val isfamily: Int,
var photoUrl: String

)
{
    fun setupUrl(){
        photoUrl = "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
    }
}
