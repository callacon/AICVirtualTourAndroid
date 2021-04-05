package com.example.aicvirtualtour.data

import androidx.room.*
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Department

/**
 * The Room database for this app
 */
@Database(entities = [Department::class, ArtworkId::class, Artwork::class], version = 4, exportSchema = false)
abstract class AICDatabase : RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao
    abstract fun artworksDao(): ArtworksDao
    abstract fun artworkDao(): ArtworkDao

    companion object {
        const val DB_NAME: String = "main_db"
    }
}