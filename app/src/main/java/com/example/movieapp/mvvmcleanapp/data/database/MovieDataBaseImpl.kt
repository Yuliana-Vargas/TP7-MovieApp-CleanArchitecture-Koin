package com.example.movieapp.mvvmcleanapp.data.database

import com.example.movieapp.mvvmcleanapp.data.service.response.Movie
import com.example.movieapp.mvvmcleanapp.data.service.util.mapToDataBaseMovie
import com.example.movieapp.mvvmcleanapp.data.service.util.mapToLocalMovie
import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {
    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getDBMovies().mapToLocalMovie()
    }
}
