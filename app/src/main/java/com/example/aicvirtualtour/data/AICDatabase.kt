package com.example.aicvirtualtour.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aicvirtualtour.fragments.ArtworkListFragment
import com.example.aicvirtualtour.fragments.DepartmentListFragment
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Department

@Dao
interface AICDao {

    @Query("SELECT * FROM departments ORDER BY title")
    fun getAllDepartments() : LiveData<List<Department>>

    @Query("SELECT * FROM artworkIds")
    fun getArtworkList(): LiveData<List<ArtworkId>>

//    @Query("SELECT * FROM departments WHERE id = :id")
//    suspend fun getAllArtworksInDepartment(id: String): LiveData<ArtworkIds>

    @Query("SELECT * FROM artworkIds WHERE id = :id")
    fun getArtworkById(id: Int): LiveData<Artwork>

    // TODO
//    suspend fun searchAllArtwork(@retrofit2.http.Query("q") searchQuery: String,
//                                 @retrofit2.http.Query("page") page: Int): Response<ArtworkIds>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartments(departments: List<Department>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworks(artworks: List<ArtworkId>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: Artwork)

}

/**
 * The Room database for this app
 */
@Database(entities = [DepartmentListFragment::class,
    ArtworkListFragment::class],
    version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun aicDao(): AICDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "aic-db").build()
        }
    }
}