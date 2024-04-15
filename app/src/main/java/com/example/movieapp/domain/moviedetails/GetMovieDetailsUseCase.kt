package com.example.movieapp.domain.moviedetails

import android.util.Log
import com.example.movieapp.data.api.MovieRepository
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.dataToUi
import com.example.movieapp.domain.toUiModel
import com.example.movieapp.ui.models.MovieDetailsUiState
import com.example.movieapp.ui.models.MovieListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieDetailsUseCase(
    private val movieRepo: MovieRepository,
    private val favouritesRepo: FavouritesRepository
) {
    suspend fun execute(movieId: Int): MovieDetailsUiState = withContext(Dispatchers.IO) {
        return@withContext try{
            movieRepo.getMovieDetails(movieId)?.let { it ->
               val movieDetails = it.toUiModel()
                movieDetails.isFavourite = favouritesRepo.checkIfFavourite(it.id)
                   MovieDetailsUiState.ShowDetails(movieDetails)
            } ?: MovieDetailsUiState.Error("There was an error. Please try again")
        } catch(e: Exception) {
            Log.e("GetMoviesSearchUseCase","Fail to retrieve movie recs: $e")
            MovieDetailsUiState.Error("There was an error. Please try again")
        }
    }
}