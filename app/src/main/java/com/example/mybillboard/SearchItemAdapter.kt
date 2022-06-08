package com.example.mybillboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybillboard.databinding.SearchItemBinding
import com.example.mybillboard.domain.SearchItem

class SearchItemAdapter(private val onSongClick: OnSongCLickListener) :
    ListAdapter<SearchItem, SearchItemAdapter.SearchItemViewHolder>(DiffCallback) {

    class SearchItemViewHolder(private var binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchItem: SearchItem, onSongCLickListener: OnSongCLickListener) {
            binding.chartItem = searchItem
            binding.chartItemLayout.setOnClickListener {
                onSongCLickListener.onClick(searchItem, binding.imgSong)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.search_item, parent, false)
        return SearchItemViewHolder(SearchItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val searchResult = this.getItem(position)
        (holder as SearchItemViewHolder).bind(searchResult, onSongClick)

    }

    class OnSongCLickListener(val cLickListener: (chartItem: SearchItem, image: View) -> Unit) {
        fun onClick(chartItem: SearchItem, image: View) = cLickListener(chartItem, image)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id.equals(newItem.id)
        }
    }
}