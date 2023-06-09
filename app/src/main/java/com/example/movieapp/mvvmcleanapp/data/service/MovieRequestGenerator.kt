package com.example.movieapp.mvvmcleanapp.data.service

import com.example.movieapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRequestGenerator {
    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder().build()
        val requestBuilder = original.newBuilder().url(url)
            .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    private val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}
