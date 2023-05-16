package com.example.movieapp.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MainViewModel.MainData

interface MainContract {
    interface MainViewModel {
        fun getValue(): LiveData<MainData>
        fun onPressedButton()
    }
}
