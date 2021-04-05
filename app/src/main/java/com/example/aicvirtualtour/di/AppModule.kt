package com.example.aicvirtualtour.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aicvirtualtour.API_BASE_URL
import com.example.aicvirtualtour.ApplicationComponent
import com.example.aicvirtualtour.BaseApplication
import com.example.aicvirtualtour.data.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
        departmentDao: DepartmentDao,
        artworksDao: ArtworksDao,
        artworkDao: ArtworkDao
    ): AICRepository {
        return AICRepository(dataSource, departmentDao, artworksDao, artworkDao)
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
    fun provideDatabase(@ApplicationContext context: Context): AICDatabase {
        return Room.databaseBuilder(
            context,
            AICDatabase::class.java,
            AICDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDepartmentDao(db: AICDatabase): DepartmentDao {
        return db.departmentDao()
    }

    @Singleton
    @Provides
    fun provideArtworksDao(db: AICDatabase): ArtworksDao {
        return db.artworksDao()
    }

    @Singleton
    @Provides
    fun provideArtworkDao(db: AICDatabase): ArtworkDao {
        return db.artworkDao()
    }
}