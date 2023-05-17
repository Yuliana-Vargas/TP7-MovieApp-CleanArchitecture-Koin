package com.example.movieapp.mvvmcleanapp.presentation.di.model

import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import com.example.movieapp.mvvmcleanapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

class MoviesModel(private val getMoviesUseCase: GetMoviesUseCase) {
    suspend fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUseCase()
}
