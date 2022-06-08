package com.example.mybillboard.network

import com.example.mybillboard.database.DatabaseChartItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkChartItem(
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

fun List<NetworkChartItem>.asDatabaseModel(): List<DatabaseChartItem> {
    return map {
        DatabaseChartItem(
                id = it.id!!,
                rank = it.rank,
                title = it.title,
                artists = it.artists,
                imageUrl = it.imageUrl
        )
    }
}