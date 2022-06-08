package com.example.mybillboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybillboard.databinding.ChartItemBinding
import com.example.mybillboard.domain.ChartItem

class ChartItemAdapter(private val onSongClick: OnSongCLickListener) :
    ListAdapter<ChartItem, ChartItemAdapter.ChartItemViewHolder>(DiffCallback) {

    class ChartItemViewHolder(private var binding: ChartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chartItem: ChartItem, onSongCLickListener: OnSongCLickListener) {
            binding.chartItem = chartItem
            binding.chartItemLayout.setOnClickListener {
                onSongCLickListener.onClick(chartItem, binding.imgSong)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.chart_item, parent, false)
        return ChartItemViewHolder(ChartItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ChartItemViewHolder, position: Int) {
        val searchResult = this.getItem(position)
        (holder as ChartItemViewHolder).bind(searchResult, onSongClick)

    }

    class OnSongCLickListener(val cLickListener: (chartItem: ChartItem, image: View) -> Unit) {
        fun onClick(chartItem: ChartItem, image: View) = cLickListener(chartItem, image)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ChartItem>() {
        override fun areItemsTheSame(oldItem: ChartItem, newItem: ChartItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChartItem, newItem: ChartItem): Boolean {
            return oldItem.id.equals(newItem.id)
        }
    }
}