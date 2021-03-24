package com.example.aicvirtualtour

import com.squareup.moshi.Json

 data class Artwork(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "image_id") val imageId: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "date_display") val dateCreated: String,
    @field:Json(name = "artist_display") val artist: String,
    @field:Json(name = "medium_display") val medium: String,
    @field:Json(name = "dimensions") val dimensions: String
)