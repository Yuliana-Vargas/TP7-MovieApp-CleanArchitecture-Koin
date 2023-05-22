package com.example.movieapp.tests.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.mvvmcleanapp.data.service.util.toMovieDB
import com.example.movieapp.mvvmcleanapp.domain.entity.Movie
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieMapperTest {
    private lateinit var movie: Movie

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movie = Movie(ID, TITLE, POSTER_PATH, VOTE_AVERAGE)
    }

    @Test
    fun `transforming a Movie entity to a MovieDB entity`() {
        movie.toMovieDB()

        assertEquals(ID, movie.id)
        assertEquals(TITLE, movie.title)
        assertEquals(POSTER_PATH, movie.posterPath)
        assertEquals(VOTE_AVERAGE, movie.voteAverage, 0.0)
    }

    companion object {
        const val ID = 12345
        const val TITLE = "Mario Bros"
        const val POSTER_PATH = "http://image.jpg"
        const val VOTE_AVERAGE = 5.1
    }
}
