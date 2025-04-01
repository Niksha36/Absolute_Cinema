package com.example.absolute_cinema.domain.repository

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getTopMovies(page: Int, limit: Int): List<MoviePoster>
    suspend fun getForKids(page: Int, limit: Int): List<MoviePoster>
    suspend fun getLatestMovies(page: Int, limit: Int, date: String): List<MoviePoster>
    suspend fun getMovieById(id: Int,  page: Int, limit: Int): MovieDetails
    suspend fun saveMovie(movie: MovieDetails, comment: List<MovieComment>)
    suspend fun deleteMovie(movieId: Int)
    suspend fun getSavedPosters(): List<MoviePoster>
    suspend fun getMovieDetails(id: Int): MovieDetails?
    fun isMovieSaved(id: Int): Flow<Boolean>
}