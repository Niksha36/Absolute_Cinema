package com.example.absolute_cinema.data.remoute

import com.example.absolute_cinema.data.remoute.dto.MovieDetail.MovieDetailDto
import com.example.absolute_cinema.data.remoute.dto.comments.CommentsDto
import com.example.absolute_cinema.data.remoute.dto.top_movies.TopMoviesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("v1.4/movie")
    suspend fun getMoviesBySelection(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster", "name", "enName", "alternativeName", "genres", "rating"),
        @Query("notNullFields") notNullFields: String = "poster.url",
        @Query("sortField") sortField: String = "rating.imdb",
        @Query("sortType") sortType: Int = -1,
        @Query("lists") lists: String,
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
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String
    ): MovieDetailDto

    @GET("v1.4/review")
    suspend fun getMovieComments(
        @Query("movieId") movieId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("X-API-KEY") apiKey: String
    ): CommentsDto

    @GET("v1.4/movie/search")
    suspend fun getMoviesByName(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") query: String,
        @Header("X-API-KEY") apiKey: String,
        @Header("accept") accept: String = "application/json"
    ) : TopMoviesDto


    @GET("v1.4/movie")
    suspend fun getMoviesByFilter(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster", "name", "enName", "alternativeName", "genres", "rating"),
        @Query("notNullFields") notNullFields: String = "poster.url",

        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int = -1,

        @Query("genres.name") genres: List<String>?,

        @Query("year") year: String?,

        @Query("rating.kp") rating: String,

        @Query("isSeries") isSeries: Boolean?,

        @Header("X-API-KEY") apiKey: String,
        @Header("accept") accept: String = "application/json"
    ): TopMoviesDto
}