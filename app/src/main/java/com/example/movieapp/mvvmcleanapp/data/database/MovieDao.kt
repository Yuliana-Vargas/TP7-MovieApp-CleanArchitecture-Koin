package com.example.movieapp.mvvmcleanapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.mvvmcleanapp.data.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity): Long

    @Query("SELECT * FROM movie")
    fun getDBMovies(): List<MovieEntity>
}
