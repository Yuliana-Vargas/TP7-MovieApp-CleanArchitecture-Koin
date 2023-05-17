package com.example.movieapp.mvvmcleanapp.domain.database

import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): CoroutineResult<List<Movie>>
}
