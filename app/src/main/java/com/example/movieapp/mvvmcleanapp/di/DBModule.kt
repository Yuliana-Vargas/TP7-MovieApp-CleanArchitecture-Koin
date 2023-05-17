package com.example.movieapp.mvvmcleanapp.di

import androidx.room.Room
import com.example.movieapp.mvvmcleanapp.data.database.MoviesRoomDataBase
import org.koin.dsl.module

object DBModule {
    private const val DB = "MovieDataBase"

    val dbModule = module {
        single { Room.databaseBuilder(get(), MoviesRoomDataBase::class.java, DB).build() }
        single { get<MoviesRoomDataBase>().movieDao() }
    }
}
