package com.example.movieapp.mvvmcleanapp.data.service.util

import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity
import com.example.movieapp.mvvmcleanapp.data.service.response.MovieList
import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData

fun MovieList.transformToList(): List<MovieData> {
    val movieList = mutableListOf<MovieData>()
    results.forEach() {
        movieList.add(
            MovieData(
                it.id,
                it.title,
                it.posterPath,
                it.voteAverage,
            ),
        )
    }
    return movieList
}

fun MovieEntity.toMovie() = MovieData(
    this.id,
    this.title,
    this.posterPath,
    this.voteAverage,
)

fun MovieData.toMovieDB() = MovieEntity(
    this.id,
    this.title,
    this.posterPath,
    this.voteAverage,
)

fun List<MovieEntity>.toMovieList() = this.map { it.toMovie() }
