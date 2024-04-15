package com.example.movieapp.ui.models

sealed interface MovieListUiState {
    class ShowMovieList(val movieList: List<MovieUiModel>): MovieListUiState
    class Error(val errorMessage: String): MovieListUiState
    data object Loading : MovieListUiState
}