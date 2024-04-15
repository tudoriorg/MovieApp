package com.example.movieapp.domain

import com.example.movieapp.data.api.models.MovieDataModel
import com.example.movieapp.data.api.models.MovieDetailsDataModel
import com.example.movieapp.data.local.LocalMovie
import com.example.movieapp.ui.models.MovieDetailsUiModel
import com.example.movieapp.ui.models.MovieUiModel

fun MovieDataModel.toUiModel() : MovieUiModel{
    //perhaps there might be additional logic needed here in the future
    val releaseYear = if(release_date.isNotBlank()) release_date.substring(0..3) else "----"
    return MovieUiModel(
        id,
        poster_path,
        releaseYear,
        String.format("%.1f", vote_average).toFloat()
    )
}

fun MovieUiModel.toLocalMovieCard(): LocalMovie {
    return LocalMovie(
        movieId,
        posterUrl,
        releaseYear,
        rating
    )
}

fun LocalMovie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id,
        posterUrl,
        releaseYear,
        rating,
        true
    )
}

fun MovieDetailsDataModel.toUiModel(): MovieDetailsUiModel {
    val releaseYear = if(release_date.isNotBlank())
        release_date.substring(0..3)
    else
        "----"
    val voteCount = "($vote_count)"
    val genres = genres.take(2).map{ it.name}
    return MovieDetailsUiModel(
        id,
        backdrop_path,
        poster_path,
        title,
        tagline,
        releaseYear,
        String.format("%.1f", vote_average).toFloat(),
        voteCount,
        genres,
        overview
    )
}

fun MovieDetailsUiModel.toLocalMovieCard(): LocalMovie {
    return LocalMovie(
        id,
        posterPath,
        releaseDate,
        voteAverage,
    )
}

fun List<MovieDataModel>.dataToUi() = map(MovieDataModel::toUiModel)
fun List<LocalMovie>.localToUi() = map(LocalMovie::toUiModel)
