package com.example.movieapp.mvvmcleanapp.domain.usecase

import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface GetMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetMoviesUseCaseImpl(
    private val moviesService: MovieService,
    private val db: MovieDataBase,
) : GetMoviesUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = moviesService.getMovies()) {
            is CoroutineResult.Success -> {
                db.insertMovies(serviceResult.data)
                db.getAllMovies()
            }
            is CoroutineResult.Failure -> db.getAllMovies()
        }
    }
}
