package com.example.movieapp.data.api

import com.example.movieapp.data.api.models.ApiMovieListResponse
import com.example.movieapp.data.api.models.MovieDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{type}")
    suspend fun getRecommendations(
        @Path("type") type: String,
        @Query("api_key") apiKey: String
    ) : Response<ApiMovieListResponse>

    @GET("search/movie")
    suspend fun getMovieSearch(
        @Query("api_key") apiKey: String,
        @Query("query") searchQuery: String)
    : Response<ApiMovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Response<MovieDetailsDataModel>
}

const val baseUrl = "https://api.themoviedb.org/3/"
const val baseImageUrl = "https://image.tmdb.org/t/p/w500/"
const val apiKey = "abfabb9de9dc58bb436d38f97ce882bc"