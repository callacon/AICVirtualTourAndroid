package com.example.aicvirtualtour.data
import android.util.Log
import com.example.aicvirtualtour.API_BASE_URL
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AICDataSource @Inject constructor(
    private val apiClient: AICApiClient
) {

    suspend fun getDepartments() = apiClient.getAllDepartments()
    suspend fun getArtworksInDepartment(departmentId: String, page: Int) = apiClient.getAllArtworksInDepartment(departmentId, page)
    suspend fun getArtwork(id: Int) = apiClient.getArtworkById(id)
    suspend fun searchArtwork(searchQuery: String, page: Int) = apiClient.searchAllArtwork(searchQuery, page)

//    private suspend fun <T> getResult(call: suspend () -> Response<T>): T {
//        try {
//            val response = call()
//            if (response.isSuccessful && response.body() != null) {
//                return response.body()!!
//            }
//            return Result.error("Error ${response.code()}: ${response.message()}")
//        } catch (e: Exception) {
//            Log.e("error:", e.message.toString())
//            return Result.error("Network call failed with error: ${e.message.toString()}")
//        }
//    }
}