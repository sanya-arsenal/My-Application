package ru.uniquenature.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.check.VerifyResult
import ru.uniquenature.myapplication.data.Movie
import ru.uniquenature.myapplication.data.loadMovies

class ListMoviesViewModel(private val check:VerifyData): ViewModel() {
    private val mutableListMovies = MutableLiveData<ViewModelListState>()

    val listMoviesState: LiveData<ViewModelListState> get() = mutableListMovies

    fun loadingMovies(context: Context){
        viewModelScope.launch {
            mutableListMovies.value = ViewModelListState.Loading
            val list = loadMovies(context)
            val newState = when(check.checkedMovies(list)){
                is VerifyResult.Error->ViewModelListState.Error("Loading error")
                is VerifyResult.Success->ViewModelListState.Success(list)
            }
            mutableListMovies.value = newState
        }
    }

    sealed class ViewModelListState{
        object Loading : ViewModelListState()
        data class Success(val list: List<Movie>):ViewModelListState()
        data class Error(val error: String):ViewModelListState()
    }
}