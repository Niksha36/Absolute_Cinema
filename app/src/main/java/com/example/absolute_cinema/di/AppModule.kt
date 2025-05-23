package com.example.absolute_cinema.di

import android.app.Application
import androidx.room.Room
import com.example.absolute_cinema.data.local.dao.MovieDatabase
import com.example.absolute_cinema.data.local.dao.MoviesDao
import com.example.absolute_cinema.data.remoute.MoviesApi
import com.example.absolute_cinema.data.repository.MovieRepositoryImpl
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): MoviesApi {
        val contentType = "application/json".toMediaType()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieRepositoryImpl(
        api: MoviesApi,
        db: MovieDatabase,
    ): MovieRepository {
        return MovieRepositoryImpl(api, db)
    }
}