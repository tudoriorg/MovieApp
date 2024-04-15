package com.example.movieapp.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.localToUi
import com.example.movieapp.domain.toLocalMovieCard
import com.example.movieapp.ui.models.MovieUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    val favouritesFlow = favouritesRepository.getFavouritesFlow()
        .map { it.localToUi()}
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun toggleFavourite(movie: MovieUiModel) {
        viewModelScope.launch{
            favouritesRepository.toggleMovieFavourite(movie.toLocalMovieCard())
        }
    }
}