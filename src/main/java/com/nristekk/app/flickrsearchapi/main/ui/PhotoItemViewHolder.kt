package com.nristekk.app.flickrsearchapi.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.data.entity.FlickrPhotoItem
import com.squareup.picasso.Picasso


/*
* ViewHolder of PhotoItem
*/
class PhotoItemViewHolder(view: View, val picasso: Picasso):RecyclerView.ViewHolder(view) {

    private val owner: TextView = view.findViewById(R.id.owner)
    private val title: TextView = view.findViewById(R.id.title)
    private val photoView: ImageView = view.findViewById(R.id.photo_view)

    private var photoItem: FlickrPhotoItem? = null


    fun bind(item: FlickrPhotoItem?) {
        item?.let {
            //combine each member to be an Url
            item.setupUrl()
        }
        this.photoItem = item
        owner.text = item?.owner?: "--"
        title.text = item?.title?: "--"
        item?.photoUrl?.let{
            picasso.load(item.photoUrl).into(photoView)
        }

    }

    companion object {
        fun create(parent: ViewGroup, picasso: Picasso): PhotoItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_photo, parent, false)
            return PhotoItemViewHolder(view, picasso)
        }
    }

}