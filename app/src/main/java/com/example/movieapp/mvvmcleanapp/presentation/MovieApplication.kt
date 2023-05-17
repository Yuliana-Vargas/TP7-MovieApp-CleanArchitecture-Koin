package com.example.movieapp.mvvmcleanapp.presentation

import android.app.Application
import com.example.movieapp.mvvmcleanapp.di.ApiModule.apiModule
import com.example.movieapp.mvvmcleanapp.di.DBModule.dbModule
import com.example.movieapp.mvvmcleanapp.di.RepositoryModule.repositoryModule
import com.example.movieapp.mvvmcleanapp.di.ServiceModule.serviceModule
import com.example.movieapp.mvvmcleanapp.di.UseCaseModule.useCaseModule
import com.example.movieapp.mvvmcleanapp.presentation.di.ModelModule.modelModule
import com.example.movieapp.mvvmcleanapp.presentation.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MovieApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApplication)

            modules(
                listOf(
                    viewModelModule,
                    modelModule,
                    repositoryModule,
                    useCaseModule,
                    dbModule,
                    serviceModule,
                    apiModule,
                ),
            )
        }
    }
}
