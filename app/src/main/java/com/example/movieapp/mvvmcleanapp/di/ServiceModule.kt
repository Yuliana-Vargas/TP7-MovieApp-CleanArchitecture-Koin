package com.example.movieapp.mvvmcleanapp.di

import com.example.movieapp.mvvmcleanapp.data.service.MovieServiceImpl
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<MovieService> { MovieServiceImpl(get()) }
    }
}
