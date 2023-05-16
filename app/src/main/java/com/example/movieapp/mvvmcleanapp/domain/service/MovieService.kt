package com.example.movieapp.mvvmcleanapp.domain.service

import com.example.movieapp.mvvmcleanapp.data.service.response.MovieList
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<MovieList>
}
