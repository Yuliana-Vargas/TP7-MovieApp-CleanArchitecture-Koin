package com.example.movieapp.mvvmcleanapp.domain.service

import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<List<MovieData>>
}
