package com.example.movieapp.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FavouritesRepositoryImpl(
    private val movieDao: MovieDao
): FavouritesRepository{

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    override fun getFavouritesFlow(): Flow<List<LocalMovie>> = with(Dispatchers.IO){
        return@with movieDao.getAll()
    }

    override suspend fun toggleMovieFavourite(movieCard: LocalMovie)= withContext(Dispatchers.IO) {
        //coroutineScope.launch {
            if(checkIfFavourite(movieCard.id)){
                movieDao.delete(movieCard)
            } else {
                movieDao.insertMovieCard(movieCard)
            }
        //}
    }

    override suspend fun checkIfFavourite(movieId: Int): Boolean = withContext(Dispatchers.IO){
        return@withContext movieDao.checkMovieFavourite(movieId)
    }
}