package com.example.movieapp.mvvmcleanapp.domain.usecase

import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface GetMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<MovieData>>
}

class GetMoviesUseCaseImpl(
    private val moviesService: MovieService,
    private val db: MovieDataBase,
) : GetMoviesUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<MovieData>> {
        return when (val serviceResult = moviesService.getMovies()) {
            is CoroutineResult.Success -> {
                db.insertMovies(serviceResult.data)
                db.getAllMovies()
            }
            is CoroutineResult.Failure -> db.getAllMovies()
        }
    }
}
