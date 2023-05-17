package com.example.movieapp.mvvmcleanapp.di

import com.example.movieapp.mvvmcleanapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.mvvmcleanapp.domain.usecase.GetMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetMoviesUseCase> { GetMoviesUseCaseImpl(get(), get()) }
    }
}
