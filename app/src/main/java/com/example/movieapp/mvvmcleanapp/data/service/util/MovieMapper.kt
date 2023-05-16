package com.example.movieapp.mvvmcleanapp.data.service.util

import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity
import com.example.movieapp.mvvmcleanapp.data.service.response.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
)

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> = map { entity ->
    Movie(
        id = entity.id,
        title = entity.title,
        posterPath = entity.posterPath,
        voteAverage = entity.voteAverage,
    )
}
