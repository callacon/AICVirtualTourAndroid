package com.example.aicvirtualtour
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AICApiClient {
    /**
     * Return the list of departments
     */
    @GET("departments?fields=id,title")
    suspend fun getDepartments(): Response<Departments>

    /**
     * Return a specified page of artwork in a department
     */
    @GET("artworks/search?limit=25")
    suspend fun getArtworksInDepartment(@Query("query[term][department_id]") id: String,
                                        @Query("page") page: Int): Response<ArtworkIds>

    /**
     * Return data for a specific piece of art
     */
    @GET("artworks/{id}")
    suspend fun getArtwork(@Path("id") id: Int): Response<Map<String, Artwork>>

    /**
     * Return a page of results from a search query
     */
    @GET("https://api.artic.edu/api/v1/artworks/search?limit=25")
    suspend fun searchArtwork(@Query("q") searchQuery: String,
                              @Query("page") page: Int): Response<ArtworkIds>
}