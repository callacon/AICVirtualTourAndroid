package com.example.aicvirtualtour.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AICRepository @Inject constructor(
    private val dataSource: AICDataSource
) {
//
//    val departments = performNetworkCall(
//        { dao.getAllDepartments() },
//        { dataSource.getDepartments() },
//        { dao.insertDepartments(it.departments) }
//    )
//
//    fun getArtworksInDepartment(departmentId: String, page: Int) = performNetworkCall (
//        { dao.getArtworkList() },
//        { dataSource.getArtworksInDepartment(departmentId, page) },
//        { dao.insertArtworks(it.ids) }
//    )
//
//    fun searchArtwork(searchQuery: String, page: Int) = performNetworkCall (
//        { dao.getArtworkList()},
//        { dataSource.searchArtwork(searchQuery, page) },
//        { dao.insertArtworks(it.ids) }
//    )
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


    /**
     * Takes an image ID and calls AIC's IIIF API to get an image. Picasso is a framework used for
     * inserting images obtained through an HTTP request into an ImageView.
     * More documentation on Picasso here: https://square.github.io/picasso/
     */
    fun loadImageFromPicasso(imageId: String): RequestCreator? {
        return Picasso.get().load("https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg")
    }
}