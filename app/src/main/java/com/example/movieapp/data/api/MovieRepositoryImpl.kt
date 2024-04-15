package com.example.movieapp.data.api

import android.util.Log
import com.example.movieapp.data.api.models.MovieDataModel
import com.example.movieapp.data.api.models.MovieDetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val api: Api) : MovieRepository {
    override suspend fun getRecommendations(type: String): List<MovieDataModel>? = withContext(Dispatchers.IO){
        val response = api.getRecommendations(type, apiKey)
        return@withContext if(response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            Log.e("MovieRepositoryImpl","Fail to retrieve movie recs: ${response.errorBody()}")
            null
        }
    }

    override suspend fun getMovieSearch(searchQuery: String): List<MovieDataModel>? = withContext(Dispatchers.IO){
        val response = api.getMovieSearch(apiKey, searchQuery)
        return@withContext if(response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            Log.e("MovieRepositoryImpl","Fail to retrieve movie search: ${response.errorBody()}")
            null
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDataModel? = withContext(Dispatchers.IO){
        val response = api.getMovieDetails(movieId, apiKey)
        return@withContext if(response.isSuccessful) {
            response.body()
        } else {
            Log.e("MovieRepositoryImpl","Fail to retrieve movie recs: ${response.errorBody()}")
            null
        }
    }
}