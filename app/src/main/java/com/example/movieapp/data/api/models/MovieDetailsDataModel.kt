package com.example.movieapp.data.api.models

data class MovieDetailsDataModel(
    val id: Int,
    val backdrop_path: String?,
    val poster_path: String?,
    val title: String,
    val tagline: String,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int,
    val genres: List<GenreDataModel>,
    val overview: String,
)