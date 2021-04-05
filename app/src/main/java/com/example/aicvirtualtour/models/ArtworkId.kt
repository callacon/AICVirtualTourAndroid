package com.example.aicvirtualtour.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Data classes used for ArtworkList/Search views.
 * Retrieving a list of artwork (artworks/search?query...) provides a different data structure
 * than getting data for a specific piece of artwork (artwork/{artwork_id}).
 * More on this at: https://api.artic.edu/docs/#collections
 * The ArtworkId data class holds the ID that can be used to get the artwork's full description
 * in a separate call. This saves the work of having to load data for every artwork in the list
 * and instead we can just load one when it's selected.
 */

data class ArtworkIds(
    @field:Json(name = "pagination") val pagination: Pagination,
    @field:Json(name = "data") val ids: List<ArtworkId>
)

@Entity(tableName = "artworkIds")
data class ArtworkId(
    @PrimaryKey
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String
)

@Entity(tableName = "pagination")
data class Pagination(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "limit") val itemsPerPage: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "current_page") val currentPage: Int
)