package com.example.absolute_cinema.data.repository

import com.example.absolute_cinema.data.local.dao.MovieDatabase
import com.example.absolute_cinema.data.mapper.entityToMovieDetails
import com.example.absolute_cinema.data.mapper.listOfCommentsMapper
import com.example.absolute_cinema.data.mapper.movieDetailMapper
import com.example.absolute_cinema.data.mapper.movieDetailsToMovieWithRelations
import com.example.absolute_cinema.data.mapper.toMoviePoster
import com.example.absolute_cinema.data.mapper.toMoviePosterList
import com.example.absolute_cinema.data.remoute.MoviesApi
import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Constants.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MoviesApi,
    private val db: MovieDatabase
) : MovieRepository {
    private val dao = db.dao

    override suspend fun getMoviesBySelection(
        page: Int,
        limit: Int,
        selectionName: String
    ): List<MoviePoster> {
        return api.getMoviesBySelection(
            apiKey = API_KEY,
            page = page,
            limit = limit,
            lists = selectionName
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
            premiereWorld = date
        ).toMoviePosterList()
    }

    override suspend fun getMovieById(id: Int, page: Int, limit: Int): MovieDetails {
        val movieFromDb = getMovieDetails(id)
        if (movieFromDb != null) {
            return movieFromDb
        }else {
            val comments = api.getMovieComments(
                movieId = id,
                page = page,
                limit = limit,
                apiKey = API_KEY
            ).listOfCommentsMapper()
            return api.getMovieDetailsByID(id, API_KEY).movieDetailMapper(comments)
        }
    }

    override suspend fun saveMovie(movie: MovieDetails, comments: List<MovieComment>) {
        val entity = movieDetailsToMovieWithRelations(movie, comments)
        dao.insertMovie(entity)
    }

    override suspend fun deleteMovie(movieId: Int) {
        dao.deleteMovie(movieId)
    }

    override fun getSavedPosters(): Flow<List<MoviePoster>> {
        return dao.getMoviePosters().map { list ->
            list.map { it.toMoviePoster() }
        }
    }

    override suspend fun getMovieDetails(id: Int): MovieDetails? {
        return dao.getMovieDetails(id)?.entityToMovieDetails()
    }

    override suspend fun getMoviesByName(name: String, limit: Int, page: Int): List<MoviePoster> {
        return api.getMoviesByName(
            apiKey = API_KEY,
            page = page,
            limit = limit,
            query = name
        ).toMoviePosterList()
    }

    override fun isMovieSaved(id: Int): Flow<Boolean> {
        return dao.isMovieSaved(id)
    }

    override suspend fun getMoviesByFilter(
        page: Int,
        limit: Int,
        sortField: String,
        genres: List<String>?,
        year: String?,
        rating: String,
        isSeries: Boolean?
    ): List<MoviePoster> {
        return api.getMoviesByFilter(
            apiKey = API_KEY,
            page = page,
            limit = limit,
            sortField = sortField,
            genres = genres,
            year = year,
            rating = rating,
            isSeries = isSeries
        ).toMoviePosterList()
    }

}