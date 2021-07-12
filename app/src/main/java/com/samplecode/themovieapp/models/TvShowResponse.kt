package com.samplecode.themovieapp.models

import com.google.gson.annotations.SerializedName

data class TvShowResponse (

    @SerializedName("page")
    val page: Long,

    @SerializedName("results")
    val results: List<TvShow>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_results")
    val totalResults: Long
)