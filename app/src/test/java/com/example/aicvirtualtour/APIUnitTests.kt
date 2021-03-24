package com.example.aicvirtualtour

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIUnitTest {
    val apiClient: AICApiClient = Retrofit.Builder().baseUrl(API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AICApiClient::class.java)

    @Test
    suspend fun api_getDepartments_returnsAll() {
        coroutineScope {
            launch(Dispatchers.Main) {
                try {
                    val response = apiClient.getDepartments()
                    val departments = response.body()!!.departments

                    assert(departments.count() == 15)

                    for (dept in departments)

                } catch (e: Exception) {
                    Log.e("error:", e.message.toString())
                }
            }
        }
    }

    @Test
}
