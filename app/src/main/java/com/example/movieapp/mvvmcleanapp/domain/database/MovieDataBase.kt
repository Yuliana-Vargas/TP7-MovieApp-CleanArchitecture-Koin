package com.example.movieapp.mvvmcleanapp.domain.database

import com.example.movieapp.mvvmcleanapp.data.service.response.Movie

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}
