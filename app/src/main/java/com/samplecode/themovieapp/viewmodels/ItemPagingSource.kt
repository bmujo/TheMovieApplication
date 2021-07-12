package com.samplecode.themovieapp.viewmodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samplecode.themovieapp.api.ApiClientInterface
import com.samplecode.themovieapp.models.Movie
import com.samplecode.themovieapp.models.MovieResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ItemPagingSource(
    private val apiClient: ApiClientInterface,
    private val query: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {

            val response: Response<MovieResponse> = if(query.isEmpty()){
                apiClient.getTrendingMovies()
            } else{
                apiClient.searchMovies(query, position)
            }

            val movieData = response.body()

            LoadResult.Page(
                data = movieData!!.results,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(movieData.results.isEmpty()) null else position + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}