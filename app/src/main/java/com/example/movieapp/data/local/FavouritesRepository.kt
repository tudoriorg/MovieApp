package com.example.movieapp.data.local

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    fun getFavouritesFlow(): Flow<List<LocalMovie>>
    suspend fun toggleMovieFavourite(movieCard: LocalMovie)
    suspend fun checkIfFavourite(movieId: Int) : Boolean
}