package com.example.mybillboard.domain

import com.squareup.moshi.Json

data class SearchItem(
    val id: String?,
    val rank: String?,
    val title: String?,
    val artists: String?,
    val imageUrl: String?,
)