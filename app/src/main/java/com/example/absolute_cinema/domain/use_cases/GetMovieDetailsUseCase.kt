package com.example.absolute_cinema.domain.use_cases

import com.example.absolute_cinema.domain.model.MovieDetails
import com.example.absolute_cinema.domain.repository.MovieRepository
import com.example.absolute_cinema.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int, page: Int, limit: Int): Resource<MovieDetails> =
        try {
            val details = repository.getMovieById(id, page, limit)
            Resource.Success(details)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
}