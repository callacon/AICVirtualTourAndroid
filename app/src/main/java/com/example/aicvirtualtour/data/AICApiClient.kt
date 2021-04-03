package com.example.aicvirtualtour.data
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.models.ArtworkIds
import com.example.aicvirtualtour.models.Departments
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AICApiClient {
    /**
     * Return the list of departments
     */
    @GET("departments?fields=id,title")
    suspend fun getAllDepartments(): Response<Departments>

    /**
     * Return a specified page of artwork in a department
     */
    @GET("artworks/search?limit=25")
    suspend fun getAllArtworksInDepartment(@Query("query[term][department_id]") id: String,
                                        @Query("page") page: Int): Response<ArtworkIds>

    /**
     * Return a page of results from a search query
     */
    @GET("https://api.artic.edu/api/v1/artworks/search?limit=25")
    suspend fun searchAllArtwork(@Query("q") searchQuery: String,
                                 @Query("page") page: Int): Response<ArtworkIds>

    /**
     * Return data for a specific piece of art
     */
    @GET("artworks/{id}")
    suspend fun getArtworkById(@Path("id") id: Int): Response<Map<String, Artwork>>
}