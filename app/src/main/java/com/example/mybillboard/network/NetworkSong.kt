package com.example.mybillboard.network

import com.example.mybillboard.database.DatabaseSong
import com.example.mybillboard.domain.Song

data class NetworkSong(
    val id: String?,
    val imageUrl: String?,
    val title: String?,
    val artists: String?,
    val album: String?,
    val label: String?,
    val released: String?,
    val youtubeId: String?,
    val spotifyUri: String?,
)

fun NetworkSong.asDomainModel(): Song {
    return Song(
        id = id,
        imageUrl = imageUrl,
        title = title,
        artists = artists,
        album = album,
        label = label,
        released = released,
        youtubeId = youtubeId,
        spotifyUri = spotifyUri,
    )
}

fun NetworkSong.asDatabaseModel(): DatabaseSong {
    return DatabaseSong(
        id = id!!,
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