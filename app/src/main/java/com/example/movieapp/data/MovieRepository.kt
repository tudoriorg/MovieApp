package com.example.movieapp.data

import com.example.movieapp.data.models.MovieDataModel

interface MovieRepository {
    suspend fun getRecommendations(type: String): List<MovieDataModel>?
}