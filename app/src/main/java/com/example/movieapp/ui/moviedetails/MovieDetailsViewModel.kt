package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.moviedetails.GetMovieDetailsUseCase
import com.example.movieapp.domain.toLocalMovieCard
import com.example.movieapp.ui.models.MovieDetailsUiModel
import com.example.movieapp.ui.models.MovieDetailsUiState
import com.example.movieapp.ui.models.MovieListUiState
import com.example.movieapp.ui.models.MovieUiModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val favouritesRepository: FavouritesRepository
) : ViewModel(){

    private val _uiState = MutableLiveData<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState : LiveData<MovieDetailsUiState> = _uiState

    fun getDetails(movieId: Int){
        viewModelScope.launch {
            _uiState.value = getMovieDetailsUseCase.execute(movieId)
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch{
            val movie = uiState.value as MovieDetailsUiState.ShowDetails
            movie.movieDetails.isFavourite = !movie.movieDetails.isFavourite
            favouritesRepository.toggleMovieFavourite(movie.movieDetails.toLocalMovieCard())
        }
    }
}