package com.example.mybillboard.network

import com.example.mybillboard.domain.SearchItem
import com.squareup.moshi.Json

data class NetworkSearchItem(
    @Json(name = "id")
    val id: String?,
    @Json(name = "rank")
    val rank: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "artists")
    val artists: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
)

fun List<NetworkSearchItem>.asDomainModel(): List<SearchItem> {
    return map {
        SearchItem(
            id = it.id,
            rank = it.rank,
            title = it.title,
            artists = it.artists,
            imageUrl = it.imageUrl

        )
    }
}