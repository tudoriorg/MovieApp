package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.home.GetRecommendationsUseCase
import com.example.movieapp.domain.toLocalMovieCard
import com.example.movieapp.ui.models.MovieListUiState
import com.example.movieapp.ui.models.MovieUiModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<MovieListUiState>(MovieListUiState.Loading)
    val uiState : LiveData<MovieListUiState> = _uiState

    fun getRecommendations(recommendationType: String){
        viewModelScope.launch {
            _uiState.value = getRecommendationsUseCase.execute(recommendationType)
        }
    }

    fun toggleFavourite(movie: MovieUiModel) {
        viewModelScope.launch{
            favouritesRepository.toggleMovieFavourite(movie.toLocalMovieCard())
        }
    }

    fun orderRatingAscending(){
        viewModelScope.launch{
            val movieList = (_uiState.value as MovieListUiState.ShowMovieList).movieList.sortedBy { it.rating }
            _uiState.value= MovieListUiState.ShowMovieList(movieList)
        }
    }

    fun orderRatingDescending(){
        viewModelScope.launch{
            val movieList = (_uiState.value as MovieListUiState.ShowMovieList).movieList.sortedByDescending { it.rating }
            _uiState.value= MovieListUiState.ShowMovieList(movieList)
        }
    }
    fun orderDateAscending(){
        viewModelScope.launch{
            val movieList = (_uiState.value as MovieListUiState.ShowMovieList).movieList.sortedBy { it.releaseYear }
            _uiState.value= MovieListUiState.ShowMovieList(movieList)
        }
    }
    fun orderDateDescending(){
        viewModelScope.launch{
            val movieList = (_uiState.value as MovieListUiState.ShowMovieList).movieList.sortedByDescending { it.releaseYear }
            _uiState.value= MovieListUiState.ShowMovieList(movieList)
        }
    }
}