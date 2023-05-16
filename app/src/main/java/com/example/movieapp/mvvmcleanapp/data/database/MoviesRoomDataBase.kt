package com.example.movieapp.mvvmcleanapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MoviesRoomDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
