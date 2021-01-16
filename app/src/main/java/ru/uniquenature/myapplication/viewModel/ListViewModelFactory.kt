package ru.uniquenature.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.data.RepositoryMovies

class ListViewModelFactory:ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        ListMoviesViewModel::class.java -> ListMoviesViewModel(VerifyData(dispatcher = Dispatchers.Default), RepositoryMovies())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}