package com.example.aicvirtualtour.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aicvirtualtour.models.Artwork

@Dao
interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artwork: Artwork)

    @Query("SELECT * FROM artworks where id = :id")
    suspend fun get(id: Int): Artwork
}