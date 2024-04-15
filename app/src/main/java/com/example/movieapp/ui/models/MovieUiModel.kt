package com.example.movieapp.ui.models

data class MovieUiModel(
    val movieId: Int,
    val posterUrl: String?,
    val releaseYear: String,
    val rating: Float,
    var isFavourite: Boolean = false,
)
