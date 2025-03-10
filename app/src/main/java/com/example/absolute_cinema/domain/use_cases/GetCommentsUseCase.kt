package com.example.absolute_cinema.domain.use_cases

import com.example.absolute_cinema.domain.model.MovieComment
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    val repository: MovieRepository
) {
    operator fun invoke(movieId: Int, page: Int, limit: Int): Flow<Resource<List<MovieComment>>> = flow {
        emit(Resource.Loading())
        try {
            val comments = repository.getMovieComments(movieId, page, limit)
            emit(Resource.Success(comments))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}