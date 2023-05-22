package com.example.movieapp.tests.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity
import com.example.movieapp.mvvmcleanapp.data.service.util.toMovie
import com.example.movieapp.mvvmcleanapp.data.service.util.toMovieList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieEntityMapperTest {
    private lateinit var movieEntity: MovieEntity
    private lateinit var movieEntityList: List<MovieEntity>

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieEntity = MovieEntity(ID, TITLE, POSTER_PATH, VOTE_AVERAGE)
        movieEntityList = mutableListOf(MovieEntity(ID, TITLE, POSTER_PATH, VOTE_AVERAGE))
    }

    @Test
    fun `transforming a Movie entity list to a MovieDB entity list`() {
        val dbListToMovieList = movieEntityList.toMovieList()

        assertEquals(ID, dbListToMovieList[0].id)
        assertEquals(TITLE, dbListToMovieList[0].title)
        assertEquals(POSTER_PATH, dbListToMovieList[0].posterPath)
        assertEquals(VOTE_AVERAGE, dbListToMovieList[0].voteAverage, 0.0)
    }

    @Test
    fun `transforming a MovieDB entity to a Movie entity`() {
        val dbToMovie = movieEntity.toMovie()

        assertEquals(ID, dbToMovie.id)
        assertEquals(TITLE, dbToMovie.title)
        assertEquals(POSTER_PATH, dbToMovie.posterPath)
        assertEquals(VOTE_AVERAGE, dbToMovie.voteAverage, 0.0)
    }

    companion object {
        const val ID = 12345
        const val TITLE = "Mario Bros"
        const val POSTER_PATH = "http://image.jpg"
        const val VOTE_AVERAGE = 5.1
    }
}
