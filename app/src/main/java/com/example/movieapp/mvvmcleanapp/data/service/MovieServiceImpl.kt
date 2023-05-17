package com.example.movieapp.mvvmcleanapp.data.service

import com.example.movieapp.mvvmcleanapp.data.service.api.MovieClient
import com.example.movieapp.mvvmcleanapp.data.service.util.transformToList
import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult

class MovieServiceImpl(private val api: MovieRequestGenerator) :
    MovieService {
    override suspend fun getMovies(): CoroutineResult<List<MovieData>> {
        try {
            val callResponse = api.createService(MovieClient::class.java).getData()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.transformToList())
                }
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(Exception(e))
        }
        return CoroutineResult.Failure(Exception())
    }
}
