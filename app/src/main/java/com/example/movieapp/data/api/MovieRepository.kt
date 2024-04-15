package com.example.movieapp.data.api

import com.example.movieapp.data.api.models.MovieDataModel
import com.example.movieapp.data.api.models.MovieDetailsDataModel

interface MovieRepository {
    suspend fun getRecommendations(type: String): List<MovieDataModel>?
    suspend fun getMovieSearch(searchQuery: String): List<MovieDataModel>?
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDataModel?
}