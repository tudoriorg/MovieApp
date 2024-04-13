package com.example.movieapp.ui.models

sealed interface HomeUiState {
    class ShowMovieRecommendations(val movieList: List<MovieUiModel>): HomeUiState
    class Error(val errorMessage: String): HomeUiState
    data object Loading : HomeUiState
}