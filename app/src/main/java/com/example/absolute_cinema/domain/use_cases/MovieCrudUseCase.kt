package com.example.absolute_cinema.domain.use_cases

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieCrudUseCase @Inject constructor(
    val repository: MovieRepository
) {
    suspend fun saveMovie(movie: MovieDetails, comment: List<MovieComment>) = repository.saveMovie(movie, comment)
    suspend fun deleteMovie(movieId: Int) = repository.deleteMovie(movieId)
    suspend fun isMovieSaved(id: Int) = repository.isMovieSaved(id)

    fun getMoviePosters(): Flow<Resource<List<MoviePoster>>> = flow{
        try {
            emit(Resource.Loading())
            val response = repository.getSavedPosters()
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}