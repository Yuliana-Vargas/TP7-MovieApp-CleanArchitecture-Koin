package com.example.movieapp.mvvmcleanapp.data.service

import com.example.movieapp.mvvmcleanapp.data.service.api.MovieClient
import com.example.movieapp.mvvmcleanapp.data.service.response.MovieList
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

class MovieServiceImpl(private val client: MovieClient) :
    MovieService {
    override suspend fun getMovies(): CoroutineResult<MovieList> {
        try {
            val response = client.getData().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it)
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
    }
}
