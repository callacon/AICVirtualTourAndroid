package com.example.aicvirtualtour
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AICApiClient {
    @GET("departments?fields=id,title&limit=25")
    suspend fun getDepartments(): Response<Departments>

    @GET("artworks/search?limit=25")
    suspend fun getArtworksInDepartment(@Query("query[term][department_id]") id: String,
                                        @Query("page") page: Int): Response<ArtworkIds>

    @GET("artworks/{id}")
    suspend fun getArtwork(@Path("id") id: Int): Response<Map<String, Artwork>>

    @GET("https://api.artic.edu/api/v1/artworks/search")
    suspend fun searchArtwork(@Query("q") searchQuery: String,
                              @Query("page") page: Int): Response<ArtworkIds>
}