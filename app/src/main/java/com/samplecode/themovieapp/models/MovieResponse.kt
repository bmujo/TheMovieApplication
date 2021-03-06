package com.samplecode.themovieapp.models

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page")
    val page: Long,

    @SerializedName("results")
    val results: List<Movie>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_results")
    val totalResults: Long
)
