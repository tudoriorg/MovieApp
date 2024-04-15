package com.example.movieapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.search.GetMoviesSearchUseCase
import com.example.movieapp.domain.toLocalMovieCard
import com.example.movieapp.ui.models.MovieListUiState
import com.example.movieapp.ui.models.MovieUiModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: GetMoviesSearchUseCase,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<MovieListUiState>(
        MovieListUiState.Loading
    )
    val uiState : LiveData<MovieListUiState> = _uiState
    fun searchMovie(searchQuery: String) {
        if (searchQuery.isBlank()) return
        viewModelScope.launch {
            _uiState.value = searchUseCase.execute(searchQuery)
        }
    }

    fun toggleFavourite(movie: MovieUiModel) {
        viewModelScope.launch{
            favouritesRepository.toggleMovieFavourite(movie.toLocalMovieCard())
        }
    }

}