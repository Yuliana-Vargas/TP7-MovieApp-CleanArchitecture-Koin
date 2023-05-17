package com.example.movieapp.mvvmcleanapp.presentation.di

import com.example.movieapp.mvvmcleanapp.presentation.di.model.MoviesModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { MoviesModel(get()) }
    }
}
