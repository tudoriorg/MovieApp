package com.example.movieapp.data

import com.example.movieapp.data.models.RecommendationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{type}")
    suspend fun getRecommendations(@Path("type") type: String, @Query("api_key") apiKey: String) : Response<RecommendationsResponse>
}

const val baseUrl = "https://api.themoviedb.org/3/"
const val baseImageUrl = "https://image.tmdb.org/t/p/w500/"
const val apiKey = ""