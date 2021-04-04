package com.example.aicvirtualtour.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.aicvirtualtour.models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AICRepository @Inject constructor(
    private val dataSource: AICDataSource,
    private val departmentDao: DepartmentDao,
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
                    artworkDao.delete()
                    artworkDao.insert(response.body()!!.ids)
                }
                val cachedArtworks = artworkDao.get()
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
                    artworkDao.delete()
                    artworkDao.insert(response.body()!!.ids)
                }
                val cachedArtworks = artworkDao.get()
                val pagination = response.body()!!.pagination
                val data = setOf(pagination, cachedArtworks)
                emit(ResponseState.Success(data))
            } catch (e: Exception) {
                emit(ResponseState.Error(e))
            }
        }
//
//    fun getArtwork(id: Int) = performNetworkCall(
//        { dao.getArtworkById(id) },
//        { dataSource.getArtwork(id) },
//        { dao.insertArtwork(it.getValue("data")) }
//    )
//
//    private fun <T, A> performNetworkCall(query: () -> LiveData<T>,
//                                          call: suspend () -> Result<A>,
//                                          save: suspend (A) -> Unit): LiveData<Result<T>> =
//        liveData(Dispatchers.IO) {
//            emit(Result.loading())
//            val source = query.invoke().map{
//                Result.success(it)
//            }
//            emitSource(source)
//
//            val response = call.invoke()
//            if (response.status == Result.Status.SUCCESS) {
//                save(response.data!!)
//            } else if (response.status == Result.Status.ERROR) {
//                emit(Result.error(response.message!!))
//                emitSource(source)
//            }
//        }

}