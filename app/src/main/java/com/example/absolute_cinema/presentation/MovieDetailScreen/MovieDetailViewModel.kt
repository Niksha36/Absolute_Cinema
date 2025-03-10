package com.example.absolute_cinema.presentation.MovieDetailScreen

import androidx.lifecycle.ViewModel
import com.example.absolute_cinema.domain.use_cases.GetCommentsUseCase
import com.example.absolute_cinema.domain.use_cases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val movieDetailsUseCase: GetMovieDetailsUseCase,
    val commentsUseCase: GetCommentsUseCase
): ViewModel() {

}