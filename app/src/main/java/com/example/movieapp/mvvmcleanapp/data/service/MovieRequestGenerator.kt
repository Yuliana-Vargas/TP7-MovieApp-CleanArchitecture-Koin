package com.example.movieapp.mvvmcleanapp.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private const val API_MOVIES_URL = "https://api.themoviedb.org"
    private const val API_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjY2I1NzQ2MGQxMzRiY2M1OTAyMWI2YzUwNTgzOTRjYSIsInN1YiI6IjYyOTdlZDE3M2ZhYmEwMDA5YjFkMDkyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wuM9MKeQE0x69B6DY5tL2m8E7RWbkrHBCDiZ13aN-JE"
    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(API_MOVIES_URL).addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build())
        val apiKeyInterceptor = Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder().build()
            val requestBuilder = original.newBuilder().url(url)
                .header("Authorization", "Bearer $API_TOKEN")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val client = httpClient.addInterceptor(apiKeyInterceptor).build()
        return retrofit.client(client).build().create(serviceClass)
    }
}
