package com.egiwon.architecturestudy.ui.tabs

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.egiwon.architecturestudy.databinding.RvContentItemBinding

class ContentViewHolder(
    private val viewType: Int,
    parent: ViewGroup,
    @LayoutRes private val layoutRes: Int
) : BaseRecyclerView.BaseViewHolder<RvContentItemBinding>(
    parent,
    layoutRes
) {

    override fun bindItem(item: Any?) {
        (item as? ContentItem)?.let {
            binding.viewType = viewType
            binding.contentItem = it
        }
    }

}