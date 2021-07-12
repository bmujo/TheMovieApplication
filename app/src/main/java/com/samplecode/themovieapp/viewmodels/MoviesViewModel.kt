package com.samplecode.themovieapp.viewmodels

import androidx.lifecycle.*
import com.samplecode.themovieapp.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: AppRepository, state: SavedStateHandle) : BaseViewModel(repository, state) {
}