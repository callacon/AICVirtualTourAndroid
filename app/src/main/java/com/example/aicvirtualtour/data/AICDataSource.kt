package com.example.aicvirtualtour.data
import android.util.Log
import com.example.aicvirtualtour.API_BASE_URL
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.models.ArtworkIds
import com.example.aicvirtualtour.models.Departments
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query
import javax.inject.Inject

class AICDataSource @Inject constructor(
    private val apiClient: AICApiClient
) {

    suspend fun getDepartments(): Response<Departments> = apiClient.getAllDepartments()

    suspend fun getArtworksInDepartment(id: String, page: Int): Response<ArtworkIds> =
        apiClient.getAllArtworksInDepartment(id, page)

    suspend fun searchArtwork(query: String, page: Int): Response<ArtworkIds> =
        apiClient.searchAllArtwork(query, page)

    suspend fun getArtwork(id: Int): Response<Map<String, Artwork>> = apiClient.getArtworkById(id)
}