package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.data.models.MovieDataModel
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
}