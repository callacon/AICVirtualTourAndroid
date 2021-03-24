package com.example.aicvirtualtour

import com.squareup.moshi.Json

/**
 * Stores the array of departments returned by apiClient.getDepartments()
 */
data class Departments(
    @field:Json(name = "data") val departments: List<Department>
)

data class Department(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String
)