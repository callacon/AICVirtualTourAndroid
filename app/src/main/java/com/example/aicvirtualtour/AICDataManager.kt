package com.example.aicvirtualtour
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AICDataManager {
    val apiClient: AICApiClient = Retrofit.Builder().baseUrl(API_BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(AICApiClient::class.java)

    /**
     * Takes an image ID and calls AIC's IIIF API to get an image. Picasso is a framework used for
     * inserting images obtained through an HTTP request into an ImageView.
     * More documentation on Picasso here: https://square.github.io/picasso/
     */
    suspend fun loadImageFromPicasso(imageId: String): RequestCreator? {
        return Picasso.get().load("https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg")
    }
}