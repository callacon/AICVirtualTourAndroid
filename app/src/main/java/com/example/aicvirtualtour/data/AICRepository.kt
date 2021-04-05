package com.example.aicvirtualtour.data

import com.example.aicvirtualtour.models.*
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This repository implements the Single Source of Truth data strategy. It will only return data that is
 * cached in the database to the view models. Each of these functions makes an API call, takes the response
 * and stores the data if successful, and then provides that cached data from the database to the
 * view model that calls it.
 */
class AICRepository @Inject constructor(
    private val dataSource: AICDataSource,
    private val departmentDao: DepartmentDao,
    private val artworksDao: ArtworksDao,
    private val artworkDao: ArtworkDao
) {
    suspend fun getDepartments(): Flow<ResponseState<List<Department>>> = flow {
        emit(ResponseState.Loading)
        try {
            val response = dataSource.getDepartments()
            departmentDao.insert(response.body()!!.departments)
            val cachedDepartments = departmentDao.get()
            emit(ResponseState.Success(cachedDepartments))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }

    fun getArtworksInDepartment(departmentId: String, page: Int): Flow<ResponseState<Set<Any>>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = dataSource.getArtworksInDepartment(departmentId, page)
                if (response.body() != null) {
                    artworksDao.delete()
                    artworksDao.insert(response.body()!!.ids)
                }
                val cachedArtworks = artworksDao.get()
                val pagination = response.body()!!.pagination
                val data = setOf(pagination, cachedArtworks)
                emit(ResponseState.Success(data))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }

    fun searchArtwork(searchQuery: String, page: Int): Flow<ResponseState<Set<Any>>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = dataSource.searchArtwork(searchQuery, page)
                if (response.body() != null) {
                    artworksDao.delete()
                    artworksDao.insert(response.body()!!.ids)
                }
                val cachedArtworks = artworksDao.get()
                val pagination = response.body()!!.pagination
                val data = setOf(pagination, cachedArtworks)
                emit(ResponseState.Success(data))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }

    fun getArtwork(id: Int): Flow<ResponseState<Artwork>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val response = dataSource.getArtwork(id)
                artworkDao.insert(response.body()?.get("data")!!)
                val cachedArtwork = artworkDao.get(id)
                emit(ResponseState.Success(cachedArtwork))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }

    /**
     * Takes an image ID and calls AIC's IIIF API to get an image. Picasso is a framework used for
     * inserting images obtained through an HTTP request into an ImageView.
     * More documentation on Picasso here: https://square.github.io/picasso/
     */
    fun loadImageFromPicasso(imageId: String): RequestCreator? {
        return Picasso.get().load("https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg")
    }

}