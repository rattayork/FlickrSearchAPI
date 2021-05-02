package com.nristekk.flickrsearchapi.history

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nristekk.app.flickrsearchapi.data.entity.History
import com.nristekk.app.flickrsearchapi.history.HistoryAdapter


/**
 * [BindingAdapter]s for the [History]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<History>) {
    (listView.adapter as HistoryAdapter).submitList(items)
}

