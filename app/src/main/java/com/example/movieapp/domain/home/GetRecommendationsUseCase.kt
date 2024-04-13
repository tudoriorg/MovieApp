package com.example.movieapp.domain.home

import com.example.movieapp.data.MovieRepository
import com.example.movieapp.domain.toUiModel
import com.example.movieapp.ui.models.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRecommendationsUseCase(
    private val movieRepo: MovieRepository
) {
    suspend fun execute(type: String): HomeUiState = withContext(Dispatchers.IO){
        return@withContext try{
            movieRepo.getRecommendations(type)?.let { movieList ->
                HomeUiState.ShowMovieRecommendations(movieList.toUiModel())
            } ?: HomeUiState.Error("There was an error. Please try again")
        } catch(e: Exception) {
             HomeUiState.Error("There was an error. Please try again")
        }
    }
}