package com.samplecode.themovieapp.api

import com.samplecode.themovieapp.models.MovieResponse
import com.samplecode.themovieapp.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClientInterface {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "API_KEY_HERE"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    @GET("movie/top_rated")
    suspend fun getTrendingMovies() : Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ) : Response<MovieResponse>

    @GET("tv/top_rated")
    suspend fun getTrendingTvShows() : Response<TvShowResponse>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("query") query: String,
        @Query("page") page: Int
    ) : Response<TvShowResponse>
}