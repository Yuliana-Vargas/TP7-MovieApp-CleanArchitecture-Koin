package com.example.movieapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun `set status with SHOW_MOVIES as value`() {
        mainViewModel.onPressedButton()

        Assert.assertEquals(
            MainViewModel.MainStatus.SHOW_MOVIES,
            mainViewModel.getValue().value?.status,
        )
    }

    @Test
    fun `set status with SHOW_DIALOG_ERROR as value`() {
        mainViewModel.onShowDialogErrorPressedButton()

        Assert.assertEquals(
            MainViewModel.MainStatus.SHOW_DIALOG_ERROR,
            mainViewModel.getValue().value?.status,
        )
    }
}
