package com.example.aicvirtualtour.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Stores the array of departments returned by apiClient.getDepartments()
 */
data class Departments(
    @field:Json(name = "data") val departments: List<Department>
)

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String
)