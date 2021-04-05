package com.example.aicvirtualtour

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.aicvirtualtour.data.AICDatabase
import com.example.aicvirtualtour.data.ArtworkDao
import com.example.aicvirtualtour.data.ArtworksDao
import com.example.aicvirtualtour.data.DepartmentDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaoTests: MockDatabase() {
    private var departmentDao: DepartmentDao? = null
    private var artworksDao: ArtworksDao? = null
    private var artworkDao: ArtworkDao? = null

    @Before
    fun setup() {
        departmentDao = db.departmentDao()
        runBlocking {
            departmentDao?.insert(mockDepartments)
        }

        artworksDao = db.artworksDao()
        runBlocking {
            artworksDao?.insert(mockArtworkIds)
        }

        artworkDao = db.artworkDao()
        runBlocking {
            artworkDao?.insert(mockArtworks[0])
            artworkDao?.insert(mockArtworks[1])
            artworkDao?.insert(mockArtworks[2])
        }
    }

    @Test
    fun testDepartmentDao() {
        runBlocking {
            val departments = departmentDao!!.get()
            assert(departments.size == 6)

            for((index, dept) in departments.withIndex()) {
                assert(dept.id == mockDepartments[index].id)
                assert(dept.title == mockDepartments[index].title)
            }
        }
    }

    @Test
    fun testArtworksDao() {
        runBlocking {
            val artworkIds = artworksDao!!.get()
            assert(artworkIds.size == 10)

            for((index, dept) in artworkIds.withIndex()) {
                assert(dept.id == mockArtworkIds[index].id)
                assert(dept.title == mockArtworkIds[index].title)
            }
        }
    }

    @Test
    fun testArtworsDao() {
        runBlocking {
            var artwork = artworkDao!!.get(1)
            assert(artwork.id == mockArtworks[0].id)
            assert(artwork.title == mockArtworks[0].title)
            assert(artwork.dateCreated == mockArtworks[0].dateCreated)
            assert(artwork.artist == mockArtworks[0].artist)
            assert(artwork.medium == mockArtworks[0].medium)
            assert(artwork.dimensions == mockArtworks[0].dimensions)

            artwork = artworkDao!!.get(2)
            assert(artwork.id == mockArtworks[1].id)
            assert(artwork.title == mockArtworks[1].title)
            assert(artwork.dateCreated == mockArtworks[1].dateCreated)
            assert(artwork.artist == mockArtworks[1].artist)
            assert(artwork.medium == mockArtworks[1].medium)
            assert(artwork.dimensions == mockArtworks[1].dimensions)

            artwork = artworkDao!!.get(3)
            assert(artwork.id == mockArtworks[2].id)
            assert(artwork.title == mockArtworks[2].title)
            assert(artwork.dateCreated == mockArtworks[2].dateCreated)
            assert(artwork.artist == mockArtworks[2].artist)
            assert(artwork.medium == mockArtworks[2].medium)
            assert(artwork.dimensions == mockArtworks[2].dimensions)
        }
    }
}