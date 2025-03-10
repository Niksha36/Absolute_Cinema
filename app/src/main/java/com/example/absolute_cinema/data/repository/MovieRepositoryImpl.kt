package com.example.absolute_cinema.data.repository

import com.example.absolute_cinema.data.mapper.listOfCommentsMapper
import com.example.absolute_cinema.data.mapper.movieDetailMapper
import com.example.absolute_cinema.data.mapper.toMoviePosterList
import com.example.absolute_cinema.data.remoute.MoviesApi
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Constants.API_KEY
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MoviesApi
): MovieRepository {
    override suspend fun getTopMovies(
        page: Int,
        limit: Int
    ): List<MoviePoster> {
        return api.getTopMovies(
            apiKey = API_KEY,
            page = page,
            limit = limit
        ).toMoviePosterList()
    }

    override suspend fun getForKids(
        page: Int,
        limit: Int
    ): List<MoviePoster> {
        return api.getForKids(
            apiKey = API_KEY,
            page = page,
            limit = limit
        ).toMoviePosterList()
    }

    override suspend fun getLatestMovies(
        page: Int,
        limit: Int,
        date: String
    ): List<MoviePoster> {
        return api.getLatestMovies(
            apiKey = API_KEY,
            page = page,
            limit = limit,
            premiereWorld = date).toMoviePosterList()
    }

    override suspend fun getMovieById(id: Int): MovieDetails {
        return api.getMovieDetailsByID(id).movieDetailMapper()
    }

    override suspend fun getMovieComments(movieId: Int, page: Int, limit: Int): List<MovieComment> {
        return api.getMovieComments(
            movieId = movieId,
            page = page,
            limit = limit,
            apiKey = API_KEY
        ).listOfCommentsMapper()
    }
}