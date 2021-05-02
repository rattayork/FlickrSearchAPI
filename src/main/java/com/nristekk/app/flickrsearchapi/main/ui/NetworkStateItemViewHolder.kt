package com.nristekk.app.flickrsearchapi.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.databinding.ItemNetworkStateBinding

class NetworkStateItemViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit

):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_network_state, parent, false)
){
        private val binding = ItemNetworkStateBinding.bind(itemView)
        private val progressBar = binding.progressBar
        private val errorMsg = binding.errorMsg
        private val retry = binding.retryButton
            .also {
                it.setOnClickListener { retryCallback() }
            }

    fun bindTo(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
        errorMsg.text = (loadState as? LoadState.Error)?.error?.message
    }
}
