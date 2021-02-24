package ru.uniquenature.myapplication.listViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.data.MoviesRepository
import ru.uniquenature.myapplication.data.RemoteDataStore

class ListViewModelFactory(private val applicationContext: Context):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        ListMoviesViewModel::class.java -> ListMoviesViewModel(VerifyData(dispatcher = Dispatchers.Default), MoviesRepository(dataStore = RemoteDataStore(),context = applicationContext ))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}