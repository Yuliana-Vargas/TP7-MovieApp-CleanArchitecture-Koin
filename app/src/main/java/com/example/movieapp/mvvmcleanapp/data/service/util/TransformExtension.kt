package com.example.movieapp.mvvmcleanapp.data.service.util

import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity
import com.example.movieapp.mvvmcleanapp.data.service.response.ResultResponse
import com.example.movieapp.mvvmcleanapp.domain.entity.Movie

fun ResultResponse.transformToList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    results.forEach() {
        movieList.add(
            Movie(
                it.id,
                it.title,
                it.posterPath,
                it.voteAverage,
            ),
        )
    }
    return movieList
}

fun MovieEntity.toMovie() = Movie(
    this.id,
    this.title,
    this.posterPath,
    this.voteAverage,
)

fun Movie.toMovieDB() = MovieEntity(
    this.id,
    this.title,
    this.posterPath,
    this.voteAverage,
)

fun List<MovieEntity>.toMovieList() = this.map { it.toMovie() }
