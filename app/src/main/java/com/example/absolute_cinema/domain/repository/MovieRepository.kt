package com.example.absolute_cinema.domain.repository

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getMoviesBySelection(page: Int, limit: Int, selectionName: String): List<MoviePoster>
    suspend fun getForKids(page: Int, limit: Int): List<MoviePoster>
    suspend fun getLatestMovies(page: Int, limit: Int, date: String): List<MoviePoster>
    suspend fun getMovieById(id: Int,  page: Int, limit: Int): MovieDetails
    suspend fun saveMovie(movie: MovieDetails, comment: List<MovieComment>)
    suspend fun deleteMovie(movieId: Int)
    fun getSavedPosters(): Flow<List<MoviePoster>>
    suspend fun getMovieDetails(id: Int): MovieDetails?
    suspend fun getMoviesByName(name: String, limit: Int, page: Int): List<MoviePoster>
    fun isMovieSaved(id: Int): Flow<Boolean>
    suspend fun getMoviesByFilter(page: Int, limit: Int, sortField: String, genres: List<String>?, year: String?, rating: String, isSeries: Boolean?): List<MoviePoster>
}