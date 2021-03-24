package com.example.aicvirtualtour

import com.squareup.moshi.Json

data class ArtworkIds(
    @field:Json(name = "pagination") val pagination: Pagination,
    @field:Json(name = "data") val ids: List<ArtworkId>
)

data class ArtworkId(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String
)

data class Pagination(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "limit") val itemsPerPage: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "current_page") val currentPage: Int
)