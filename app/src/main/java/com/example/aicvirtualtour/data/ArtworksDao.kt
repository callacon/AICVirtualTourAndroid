package com.example.aicvirtualtour.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.ArtworkIds
import com.example.aicvirtualtour.models.Pagination

@Dao
interface ArtworksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artworks: List<ArtworkId>)

    @Query("SELECT * FROM artworkIds")
    suspend fun get(): List<ArtworkId>

    @Query("DELETE FROM artworkIds")
    suspend fun delete()
}