package com.example.movieapp.mvvmcleanapp.presentation.di

import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MainViewModel
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { MainViewModel() }
        viewModel { MoviesViewModel(get()) }
    }
}
