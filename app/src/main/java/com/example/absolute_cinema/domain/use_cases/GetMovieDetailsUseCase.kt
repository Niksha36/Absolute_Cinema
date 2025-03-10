package com.example.absolute_cinema.domain.use_cases

import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    val repository: MovieRepository
) {

    operator fun invoke(id: Int): Flow<Resource<MovieDetails>> = flow{
        emit(Resource.Loading())
        try {
            val details = repository.getMovieById(id)
            emit(Resource.Success(details))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}