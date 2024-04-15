package com.example.movieapp.ui.models

sealed interface MovieDetailsUiState {
    class ShowDetails(val movieDetails: MovieDetailsUiModel): MovieDetailsUiState
    class Error(val errorMessage: String): MovieDetailsUiState
    data object Loading : MovieDetailsUiState
}