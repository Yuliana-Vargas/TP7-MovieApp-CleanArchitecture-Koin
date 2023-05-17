package com.example.movieapp.mvvmcleanapp.di

import com.example.movieapp.mvvmcleanapp.data.service.MovieRequestGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { MovieRequestGenerator() }
    }
}
