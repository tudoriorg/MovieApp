package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM localmovie")
    fun getAll(): Flow<List<LocalMovie>>

    @Query("SELECT EXISTS(SELECT * FROM localmovie WHERE id = :movieId)")
    fun checkMovieFavourite(movieId: Int): Boolean

    @Upsert
    fun insertMovieCard(movie: LocalMovie)

    @Delete
    fun delete(movie: LocalMovie)

}