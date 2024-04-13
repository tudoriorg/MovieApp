package com.example.movieapp.domain

import com.example.movieapp.data.models.MovieDataModel
import com.example.movieapp.ui.models.MovieUiModel

fun MovieDataModel.toUiModel() : MovieUiModel{
    //perhaps there might be additional logic needed here in the future
    return MovieUiModel(
        id,
        poster_path,
        release_date,
        vote_average
    )
}

fun List<MovieDataModel>.toUiModel() : List<MovieUiModel> {
    return map{it.toUiModel()}
}