package com.example.movieapp.mvvmcleanapp.data.database

import com.example.movieapp.mvvmcleanapp.data.service.util.toMovieDB
import com.example.movieapp.mvvmcleanapp.data.service.util.toMovieList
import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {
    override suspend fun insertMovies(movies: List<MovieData>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.toMovieDB())
        }
    }

    override suspend fun getAllMovies(): CoroutineResult<List<MovieData>> =
        movieDao.getDBMovies().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.toMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }
}
