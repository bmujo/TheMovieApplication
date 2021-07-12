package com.samplecode.themovieapp.viewmodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samplecode.themovieapp.api.ApiClientInterface
import com.samplecode.themovieapp.models.TvShow
import com.samplecode.themovieapp.models.TvShowResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TvShowPagingSource(
    private val apiClient: ApiClientInterface,
    private val query: String
) : PagingSource<Int, TvShow>() {

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {

            val response: Response<TvShowResponse> = if(query.isEmpty()){
                apiClient.getTrendingTvShows()
            } else{
                apiClient.searchTvShows(query, position)
            }

            val tvShowData = response.body()

            LoadResult.Page(
                data = tvShowData!!.results,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(tvShowData.results.isEmpty()) null else position + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}