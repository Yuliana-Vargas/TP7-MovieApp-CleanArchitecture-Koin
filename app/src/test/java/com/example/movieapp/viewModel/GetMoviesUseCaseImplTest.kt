package com.example.movieapp.viewModel

import com.example.movieapp.mvvmcleanapp.domain.database.MovieDataBase
import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import com.example.movieapp.mvvmcleanapp.domain.service.MovieService
import com.example.movieapp.mvvmcleanapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.mvvmcleanapp.domain.usecase.GetMoviesUseCaseImpl
import com.example.movieapp.mvvmcleanapp.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseImplTest {
    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var db: MovieDataBase

    @MockK
    private lateinit var movieList: List<Movie>

    private lateinit var getMoviesListUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        getMoviesListUseCase = GetMoviesUseCaseImpl(movieService, db)
    }

    @Test
    fun `if usecase returns success`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Success(movieList)
        coEvery { db.getAllMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMoviesListUseCase() }

        coVerify { db.insertMovies(movieList) }
        coVerify { db.getAllMovies() }

        assertTrue(result is CoroutineResult.Success)
        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `if usecase return failure - empty DB`() {
        coEvery { movieService.getMovies() }.returns(CoroutineResult.Failure(Exception(MSG)))
        coEvery { db.getAllMovies() }.returns(CoroutineResult.Failure(Exception(MSG)))

        val result = runBlocking { getMoviesListUseCase() }

        coVerify { db.getAllMovies() }

        assertEquals(MSG, (result as CoroutineResult.Failure).exception.message)
    }

    @Test
    fun `if usecase return failure - !empty DB`() {
        coEvery { movieService.getMovies() }.returns(CoroutineResult.Failure(Exception(MSG)))
        coEvery { db.getAllMovies() }.returns(CoroutineResult.Success(movieList))

        val result = runBlocking { getMoviesListUseCase() }

        coVerify { db.getAllMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    companion object {
        private const val MSG = "ERROR"
    }
}
