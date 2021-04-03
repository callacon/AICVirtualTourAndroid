package com.example.aicvirtualtour.di

import android.content.Context
import androidx.room.RoomDatabase
import com.example.aicvirtualtour.API_BASE_URL
import com.example.aicvirtualtour.BaseApplication
import com.example.aicvirtualtour.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(AppComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: AICDataSource,
        dao: AICDao
    ): AICRepository {
        return AICRepository(dataSource, dao)
    }

    @Singleton
    @Provides
    fun provideDataSource(
        apiClient: AICApiClient
    ): AICDataSource {
        return AICDataSource(apiClient)
    }

    @Singleton
    @Provides
    fun provideApiClient(): AICApiClient {
        return Retrofit.Builder().baseUrl(API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AICApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(app: BaseApplication): RoomDatabase {
        return AppDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase): AICDao {
        return db.aicDao()
    }
}