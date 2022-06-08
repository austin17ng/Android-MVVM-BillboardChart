package com.example.mybillboard

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybillboard.domain.SearchItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    Glide.with(imageView.context)
        .load(imgUrl)
//        .error(R.drawable.img_no_internet)
//        .placeholder(R.drawable.img_no_internet)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("searchItems")
fun bindSearchItems(recyclerView: RecyclerView, data: List<SearchItem>?) {
    val adapter = recyclerView.adapter as SearchItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("searchItems")
fun bindSearchItems(textView: TextView, data: List<SearchItem>?) {
    if (data == null) {
        textView.text = ""
    } else if (data.size == 0) {
        textView.text = "No Results. Please try again"
    } else {
        textView.text = "Search Results"
    }
}

@BindingAdapter("videoUrl")
fun bindVideo(youTubePlayer: YouTubePlayerView, videoUrl: String?) {
    youTubePlayer.addYouTubePlayerListener(
        object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                videoUrl?.let {
                    youTubePlayer.loadVideo(it, 0.0F)
                    youTubePlayer.pause()
                }
            }
        }
    )
}
