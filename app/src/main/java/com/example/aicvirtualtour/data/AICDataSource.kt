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

    /**
     * suspend fun getArtworkById(@Path("id") id: Int): Response<Map<String, Artwork>>
     */

    /**
     * Takes an image ID and calls AIC's IIIF API to get an image. Picasso is a framework used for
     * inserting images obtained through an HTTP request into an ImageView.
     * More documentation on Picasso here: https://square.github.io/picasso/
     */
    fun loadImageFromPicasso(imageId: String): RequestCreator? {
        return Picasso.get().load("https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg")
    }
}