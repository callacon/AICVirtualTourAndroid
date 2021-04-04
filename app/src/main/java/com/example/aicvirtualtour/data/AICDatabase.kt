package com.example.aicvirtualtour.data

import androidx.room.*
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Department

//@Dao
//interface AICDao {
//
//    @Query("SELECT * FROM departments ORDER BY title")
//    fun getAllDepartments() : LiveData<List<Department>>
//
//    @Query("SELECT * FROM artworkIds")
//    fun getArtworkList(): LiveData<List<ArtworkId>>
//
////    @Query("SELECT * FROM departments WHERE id = :id")
////    suspend fun getAllArtworksInDepartment(id: String): LiveData<ArtworkIds>
//
//    @Query("SELECT * FROM artworkIds WHERE id = :id")
//    fun getArtworkById(id: Int): LiveData<Artwork>
//
//    // TODO
////    suspend fun searchAllArtwork(@retrofit2.http.Query("q") searchQuery: String,
////                                 @retrofit2.http.Query("page") page: Int): Response<ArtworkIds>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDepartments(departments: List<Department>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertArtworks(artworks: List<ArtworkId>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertArtwork(artwork: Artwork)
//
//}

/**
 * The Room database for this app
 */
@Database(entities = [Department::class, ArtworkId::class], version = 3, exportSchema = false)
abstract class AICDatabase : RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao
    abstract fun artworkDao(): ArtworkDao

    companion object {
        val DB_NAME: String = "main_db"
    }
}