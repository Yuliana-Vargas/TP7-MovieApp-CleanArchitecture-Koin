/*package com.example.movieapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.mvvm.contract.MoviesContract
import com.example.movieapp.mvvmcleanapp.data.service.response.Movie
import com.example.movieapp.mvvmcleanapp.presentation.di.model.MoviesModel
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult
import com.example.movieapp.mvvmcleanapp.presentation.mvvm.viewmodel.MoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var moviesViewModel: MoviesContract.ViewModel

    @MockK
    private lateinit var moviesModel: MoviesModel

    @MockK
    private lateinit var movies: List<Movie>

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(moviesModel)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `on CoroutineResult Success, status should be SHOW_BUTTON_PRESSED`() {
        coEvery { moviesModel.getMovies() } returns CoroutineResult.Success(movies)

        runBlocking { moviesViewModel.callService().join() }

        assertEquals(
            MoviesViewModel.MovieStatus.SHOW_BUTTON_PRESSED,
            moviesViewModel.getValue().value?.status,
        )
        assertEquals(
            movies,
            moviesViewModel.getValue().value?.movies,
        )
    }

    @Test
    fun `on CoroutineResult Failure, status should be EMPTY_STATE`() {
        coEvery { moviesModel.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking { moviesViewModel.callService().join() }

        assertEquals(
            MoviesViewModel.MovieStatus.EMPTY_STATE,
            moviesViewModel.getValue().value?.status,
        )
        assertEquals(
            emptyList<Movie>(),
            moviesViewModel.getValue().value?.movies,
        )
    }

    @Test
    fun `on back button pressed, status should be BACK_BUTTON_PRESSED`() {
        moviesViewModel.onBackButtonPressed()

        assertEquals(
            MoviesViewModel.MovieStatus.BACK_BUTTON_PRESSED,
            moviesViewModel.getValue().value?.status,
        )
    }
}
*/