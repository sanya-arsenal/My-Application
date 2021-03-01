package ru.uniquenature.myapplication.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin

import org.koin.dsl.module
import ru.uniquenature.myapplication.data.MoviesDataBase
import ru.uniquenature.myapplication.data.MoviesRepository
import ru.uniquenature.myapplication.data.RemoteDataStore
import ru.uniquenature.myapplication.detailsViewModel.DetailsMovieViewModel
import ru.uniquenature.myapplication.listViewModel.ListMoviesViewModel

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val movieListModule = module {
            single { RemoteDataStore() }
            single { MoviesDataBase.create(get()) }
            single { MoviesRepository(get(),get()) }
            viewModel { ListMoviesViewModel(get(),get()) }
        }

        val movieDetailsModule = module {
            single { MoviesRepository(get(),get()) }
            viewModel { DetailsMovieViewModel(get(),get()) }
        }

        startKoin{
            androidContext(this@MyApplication)
            modules(movieListModule)
            modules(movieDetailsModule)
        }
    }

}