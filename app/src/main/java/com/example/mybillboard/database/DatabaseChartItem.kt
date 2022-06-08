package com.example.mybillboard.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybillboard.domain.ChartItem

@Entity(tableName = "chartItem")
data class DatabaseChartItem (
    @PrimaryKey
    val id: String,
    val rank: String?,
    val title: String?,
    val artists: String?,
    val imageUrl: String?,
)

fun List<DatabaseChartItem>.asDomainModel() : List<ChartItem> {
    return map {
        ChartItem(
            id = it.id,
            rank =  it.rank,
            title = it.title,
            artists = it.artists,
            imageUrl = it.imageUrl
        )
    }
}