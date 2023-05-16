package com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.mvvm.contract.MainContract

class MainViewModel : ViewModel(), MainContract.MainViewModel {
    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    override fun getValue(): LiveData<MainData> = mutableLiveData
    override fun onPressedButton() {
        mutableLiveData.postValue(MainData(MainStatus.SHOW_MOVIES))
    }

    data class MainData(
        val status: MainStatus,
    )

    enum class MainStatus {
        SHOW_MOVIES,
    }
}
