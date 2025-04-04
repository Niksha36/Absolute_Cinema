package com.example.absolute_cinema.domain.use_cases

import com.example.absolute_cinema.domain.model.MoviePoster
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesByFilterUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        page: Int,
        limit: Int,
        sortField: String,
        genres: List<String>?,
        year: String?,
        rating: String,
        isSeries: Boolean?
    ): Flow<Resource<List<MoviePoster>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getMoviesByFilter(
                page = page,
                limit = limit,
                sortField = sortField,
                genres = genres,
                year = year,
                rating = rating,
                isSeries = isSeries
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}