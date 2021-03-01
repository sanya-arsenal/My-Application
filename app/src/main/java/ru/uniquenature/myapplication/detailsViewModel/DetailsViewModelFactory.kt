package ru.uniquenature.myapplication.detailsViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.data.MoviesRepository
import ru.uniquenature.myapplication.data.RemoteDataStore

class DetailsViewModelFactory(private val applicationContext: Context): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        DetailsMovieViewModel::class.java-> DetailsMovieViewModel(VerifyData(dispatcher = Dispatchers.Default), MoviesRepository(dataStore = RemoteDataStore(),context = applicationContext ))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    }as T
}