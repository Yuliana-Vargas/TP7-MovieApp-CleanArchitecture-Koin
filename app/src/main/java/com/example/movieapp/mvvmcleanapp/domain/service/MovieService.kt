package com.example.movieapp.mvvmcleanapp.domain.service

import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<List<Movie>>
}
