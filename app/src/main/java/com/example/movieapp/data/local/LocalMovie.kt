package com.example.movieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalMovie(
    @PrimaryKey val id: Int,
    @ColumnInfo val posterUrl: String?,
    @ColumnInfo val releaseYear: String,
    @ColumnInfo val rating: Float,
)
