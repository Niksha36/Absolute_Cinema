package com.example.absolute_cinema.data.remoute

import com.example.absolute_cinema.data.remoute.dto.MovieDetail.MovieDetailDto
import com.example.absolute_cinema.data.remoute.dto.comments.CommentsDto
import com.example.absolute_cinema.data.remoute.dto.top_movies.TopMoviesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesApi {
    @GET("v1.4/movie")
    suspend fun getTopMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster", "name", "enName", "alternativeName", "genres", "rating"),
        @Query("notNullFields") notNullFields: String = "poster.url",
        @Query("sortField") sortField: String = "rating.imdb",
        @Query("sortType") sortType: Int = -1,
        @Query("lists") lists: String = "top500",
        @Header("X-API-KEY") apiKey: String,
        @Header("accept") accept: String = "application/json"
    ): TopMoviesDto

    @GET("v1.4/movie")
    suspend fun getForKids(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster", "name", "enName", "alternativeName", "genres", "rating"),
        @Query("notNullFields") notNullFields: String = "poster.url",
        @Query("sortField") sortField: String = "rating.imdb",
        @Query("sortType") sortType: Int = -1,
        @Query("ageRating") ageRating: String = "0-12",
        @Header("X-API-KEY") apiKey: String,
        @Header("accept") accept: String = "application/json"
    ): TopMoviesDto

    @GET("v1.4/movie")
    suspend fun getLatestMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster", "name", "enName", "alternativeName", "genres", "rating"),
        @Query("notNullFields") notNullFields: String = "poster.url",
        @Query("sortField") sortField: String = "rating.imdb",
        @Query("sortType") sortType: Int = -1,
        @Query("premiere.world") premiereWorld: String,
        @Header("X-API-KEY") apiKey: String,
        @Header("accept") accept: String = "application/json"
    ): TopMoviesDto

    @GET("v1.4/movie/{id}")
    suspend fun getMovieDetailsByID(
        @Query("id") id: Int
    ): MovieDetailDto

    @GET("v1.4/movie/review")
    suspend fun getMovieComments(
        @Query("movieId") movieId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("X-API-KEY") apiKey: String
    ): CommentsDto
}