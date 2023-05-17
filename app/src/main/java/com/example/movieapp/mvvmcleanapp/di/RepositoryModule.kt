package com.example.movieapp.mvvmcleanapp.di

import com.example.movieapp.mvvmcleanapp.data.database.MovieDataBaseImpl
import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<MovieDataBase> { MovieDataBaseImpl(get()) }
    }
}
