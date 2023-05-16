package com.example.movieapp.mvvmcleanapp.data.service.api

import com.example.movieapp.mvvmcleanapp.data.service.response.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

const val API_TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjY2I1NzQ2MGQxMzRiY2M1OTAyMWI2YzUwNTgzOTRjYSIsInN1YiI6IjYyOTdlZDE3M2ZhYmEwMDA5YjFkMDkyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wuM9MKeQE0x69B6DY5tL2m8E7RWbkrHBCDiZ13aN-JE"

interface MovieClient {
    @GET("/3/movie/popular")
    @Headers("Authorization: Bearer $API_TOKEN")
    fun getData(): Call<MovieList>
}
