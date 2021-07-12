package com.samplecode.themovieapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.samplecode.themovieapp.api.ApiClientInterface
import com.samplecode.themovieapp.models.MovieResponse
import com.samplecode.themovieapp.viewmodels.ItemPagingSource
import com.samplecode.themovieapp.viewmodels.TvShowPagingSource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val apiClient: ApiClientInterface) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(apiClient, query)}
        ).liveData

    fun getTvShowSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvShowPagingSource(apiClient, query)}
        ).liveData
}