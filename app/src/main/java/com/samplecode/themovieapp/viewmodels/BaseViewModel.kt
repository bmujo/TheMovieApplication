package com.samplecode.themovieapp.viewmodels

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.samplecode.themovieapp.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(private val repository: AppRepository, state: SavedStateHandle) : ViewModel() {
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val _queryStateMutableData = MutableLiveData<String>()
    val queryStateLiveData: LiveData<String>
        get() = _queryStateMutableData

    val listOfMovies = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun search(query: String?){
        currentQuery.value = query
    }

    val listOfTvShows = currentQuery.switchMap { queryString ->
        repository.getTvShowSearchResults(queryString).cachedIn(viewModelScope)
    }

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = ""
    }


}