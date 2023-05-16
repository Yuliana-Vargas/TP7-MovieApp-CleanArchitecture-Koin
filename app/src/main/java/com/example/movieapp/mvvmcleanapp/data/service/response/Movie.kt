package com.example.movieapp.mvvmcleanapp.data.service.response

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
)
