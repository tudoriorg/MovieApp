package com.example.movieapp.ui.models

import com.example.movieapp.data.api.models.GenreDataModel

data class MovieDetailsUiModel(
    val id: Int,
    val backdropPath: String?,
    val posterPath: String?,
    val title: String,
    val tagline: String,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: String,
    val genres: List<String>,
    val overview: String,
    var isFavourite: Boolean = false,
)
