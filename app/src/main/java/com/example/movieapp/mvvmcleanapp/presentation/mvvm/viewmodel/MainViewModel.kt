package com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mutableLiveData: MutableLiveData<MainData> =
        MutableLiveData()

    fun getValue(): LiveData<MainData> = mutableLiveData
    fun onPressedButton() {
        mutableLiveData.postValue(MainData(MainStatus.SHOW_MOVIES))
    }

    fun onShowDialogErrorPressedButton(){
        mutableLiveData.postValue(MainData(MainStatus.SHOW_DIALOG_ERROR))
    }

    data class MainData(
        val status: MainStatus,
    )

    enum class MainStatus {
        SHOW_MOVIES, SHOW_DIALOG_ERROR
    }
}
