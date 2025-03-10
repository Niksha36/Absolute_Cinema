package com.example.absolute_cinema.domain.repository

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster


interface MovieRepository {
    suspend fun getTopMovies(page: Int, limit: Int): List<MoviePoster>
    suspend fun getForKids(page: Int, limit: Int): List<MoviePoster>
    suspend fun getLatestMovies(page: Int, limit: Int, date: String): List<MoviePoster>
    suspend fun getMovieById(id: Int): MovieDetails
    suspend fun getMovieComments(movieId: Int, page: Int, limit: Int): List<MovieComment>
}