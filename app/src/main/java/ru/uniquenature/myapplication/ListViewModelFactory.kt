package ru.uniquenature.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import ru.uniquenature.myapplication.check.VerifyData

class ListViewModelFactory:ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass){
        ListMoviesViewModel::class.java ->ListMoviesViewModel(VerifyData(dispatcher = Dispatchers.Default))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}