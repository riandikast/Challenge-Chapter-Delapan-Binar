package com.binar.listmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.listmovie.data.movie.GetAllMovieItem
import com.binar.listmovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMovie @Inject constructor(private val api: MovieRepository): ViewModel() {
    private val popularMovieState = MutableStateFlow(emptyList<GetAllMovieItem>())
    val dataPopularMovieState : StateFlow<List<GetAllMovieItem>>
    get() = popularMovieState

    private val newMovieState = MutableStateFlow(emptyList<GetAllMovieItem>())
    val dataNewMovieState : StateFlow<List<GetAllMovieItem>>
    get() = newMovieState

    private val recMovieState = MutableStateFlow(emptyList<GetAllMovieItem>())
    val dataRecMovieState : StateFlow<List<GetAllMovieItem>>
        get() = recMovieState


    init {
        viewModelScope.launch {
            val popularMovie = api.getPopular()
            popularMovieState.value = popularMovie

            val newMovie = api.getNew()
            newMovieState.value = newMovie

            val recMovie = api.getRec()
            recMovieState.value = recMovie
        }

    }


}
