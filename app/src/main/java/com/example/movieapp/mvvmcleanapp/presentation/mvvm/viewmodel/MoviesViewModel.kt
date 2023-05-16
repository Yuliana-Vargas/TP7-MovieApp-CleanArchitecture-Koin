package com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvmcleanapp.data.service.response.Movie
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val model: MoviesContract.Model) : ViewModel(), MoviesContract.ViewModel {
    private val mutableLiveData: MutableLiveData<MovieData> = MutableLiveData()
    override fun getValue(): LiveData<MovieData> = mutableLiveData

    override fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MovieData(MovieStatus.SHOW_BUTTON_PRESSED, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MovieData(MovieStatus.EMPTY_STATE, emptyList())
                }
            }
        }
    }

    override fun onBackButtonPressed() {
        mutableLiveData.postValue(MovieData(MovieStatus.BACK_BUTTON_PRESSED, emptyList()))
    }

    data class MovieData(
        val status: MovieStatus,
        val movies: List<Movie>,
    )

    enum class MovieStatus {
        SHOW_BUTTON_PRESSED, BACK_BUTTON_PRESSED, EMPTY_STATE,
    }
}
