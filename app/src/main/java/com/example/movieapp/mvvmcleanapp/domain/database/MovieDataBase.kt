package com.example.movieapp.mvvmcleanapp.domain.database

import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface MovieDataBase {
    suspend fun insertMovies(movies: List<MovieData>)
    suspend fun getAllMovies(): CoroutineResult<List<MovieData>>
}
