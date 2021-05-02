package com.nristekk.app.flickrsearchapi.main.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nristekk.app.flickrsearchapi.data.entity.FlickrPhotoItem
import com.squareup.picasso.Picasso


/*
An adapter class to present photo item
 */
class PhotoAdapter(private  val picasso: Picasso): PagingDataAdapter<FlickrPhotoItem, PhotoItemViewHolder>(UiModelComparator) {

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        return PhotoItemViewHolder.create(parent, picasso)
    }

    object UiModelComparator : DiffUtil.ItemCallback<FlickrPhotoItem>() {

        override fun areItemsTheSame(oldItem: FlickrPhotoItem, newItem: FlickrPhotoItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FlickrPhotoItem, newItem: FlickrPhotoItem): Boolean =
            oldItem == newItem

    }
}