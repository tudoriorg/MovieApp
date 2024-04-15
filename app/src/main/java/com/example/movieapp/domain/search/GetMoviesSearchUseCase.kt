package com.example.movieapp.domain.search

import android.util.Log
import com.example.movieapp.data.api.MovieRepository
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.domain.dataToUi
import com.example.movieapp.ui.models.MovieListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMoviesSearchUseCase(
    private val movieRepo: MovieRepository,
    private val favouritesRepo: FavouritesRepository
) {
    suspend fun execute(searchQuery: String): MovieListUiState = withContext(Dispatchers.IO){
        return@withContext try{
            movieRepo.getMovieSearch(searchQuery)?.let { movieList ->
                val movieUiList = movieList.dataToUi()
                movieUiList.forEach {
                    it.isFavourite = favouritesRepo.checkIfFavourite(it.movieId)
                }
                MovieListUiState.ShowMovieList(movieUiList)
            } ?: MovieListUiState.Error("There was an error. Please try again")
        } catch(e: Exception) {
            Log.e("GetMoviesSearchUseCase","Fail to retrieve movie recs: $e")
            MovieListUiState.Error("There was an error. Please try again")
        }
    }
}