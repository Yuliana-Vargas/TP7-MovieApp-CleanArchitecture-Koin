package com.example.movieapp.mvvmcleanapp.di.model

import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvmcleanapp.data.service.MovieServiceImpl
import com.example.movieapp.mvvmcleanapp.data.service.response.Movie
import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

class MoviesModel(
    private val service: MovieServiceImpl,
    private val database: MovieDataBase,
) : MoviesContract.Model {
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                if (movies.data.results.isNotEmpty()) {
                    database.insertMovies(movies.data.results)
                    CoroutineResult.Success(database.getAllMovies())
                } else {
                    CoroutineResult.Failure(Exception())
                }
            }

            is CoroutineResult.Failure -> {
                if (database.getAllMovies().isNotEmpty()) {
                    CoroutineResult.Success(database.getAllMovies())
                } else {
                    CoroutineResult.Failure(Exception())
                }
            }
        }
    }
}
