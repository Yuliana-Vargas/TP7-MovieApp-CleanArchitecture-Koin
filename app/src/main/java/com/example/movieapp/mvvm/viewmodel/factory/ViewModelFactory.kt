package com.example.movieapp.mvvm.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel

class ViewModelFactory(private val params: Array<Any>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MoviesViewModel::class.java -> MoviesViewModel(params[0] as MoviesContract.Model) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
