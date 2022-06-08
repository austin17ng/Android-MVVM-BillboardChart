package com.example.mybillboard.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mybillboard.domain.Song

@Entity(tableName = "song")
data class DatabaseSong(
    @PrimaryKey
    val id: String,
    val imageUrl: String?,
    val title: String?,
    val artists: String?,
    val album: String?,
    val label: String?,
    val released: String?,
    val youtubeId: String?,
    val spotifyUri: String?,
)

fun DatabaseSong.asDomainModel() : Song {
    return Song(
        id = id,
        imageUrl = imageUrl,
        title = title,
        artists = artists,
        album = album,
        label = label,
        released = released,
        youtubeId = youtubeId,
        spotifyUri = spotifyUri
    )
}