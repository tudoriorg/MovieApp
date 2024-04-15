package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [LocalMovie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}