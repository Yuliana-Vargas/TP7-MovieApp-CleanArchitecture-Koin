package com.example.movieapp.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.movieapp.mvvmcleanapp.data.service.response.Movie
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel
import kotlinx.coroutines.Job

interface MoviesContract {
    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

    interface ViewModel {
        fun getValue(): LiveData<MoviesViewModel.MovieData>
        fun callService(): Job
        fun onBackButtonPressed()
    }
}
