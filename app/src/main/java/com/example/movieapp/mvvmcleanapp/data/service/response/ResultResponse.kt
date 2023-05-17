package com.example.movieapp.mvvmcleanapp.data.service.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<MovieResponse>,
)
