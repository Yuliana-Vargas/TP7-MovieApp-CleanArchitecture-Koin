package com.example.movieapp.mvvmcleanapp.data.service.api

import com.example.movieapp.BuildConfig
import com.example.movieapp.mvvmcleanapp.data.service.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieClient {
    @GET("/3/movie/popular")
    @Headers("Authorization: Bearer ${BuildConfig.API_TOKEN}")
    fun getData(): Call<ResultResponse>
}
