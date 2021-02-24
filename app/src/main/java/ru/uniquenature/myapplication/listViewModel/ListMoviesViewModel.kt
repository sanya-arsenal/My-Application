package ru.uniquenature.myapplication.listViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.check.VerifyResult
import ru.uniquenature.myapplication.data.Movie
import ru.uniquenature.myapplication.data.MoviesRepository

class ListMoviesViewModel(private val check: VerifyData, private val repository:MoviesRepository): ViewModel() {
    private val mutableListMovies = MutableLiveData<ViewModelListState>()

    val listMoviesState: LiveData<ViewModelListState> get() = mutableListMovies

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${viewModelScope.isActive}", throwable)
    }

    @ExperimentalSerializationApi
    fun loadingMovies(){
        viewModelScope.launch(exceptionHandler) {
            mutableListMovies.value = ViewModelListState.Loading
            val list = repository.readMoviesDB()
            list.sortedBy { it.ratings }
            val newState = when(check.checkedMovies(list)){
                is VerifyResult.Error-> ViewModelListState.Error("Loading error DB")
                is VerifyResult.Success-> ViewModelListState.Success(list)
            }
            mutableListMovies.value = newState

            val list1 = repository.loadMovies()
            list1.sortedBy { it.ratings }
            val newState1 = when(check.checkedMovies(list1)){
                is VerifyResult.Error-> ViewModelListState.Error("Loading error")
                is VerifyResult.Success-> ViewModelListState.Success(list1)
            }
            mutableListMovies.value = newState1
            repository.saveMoviesDB(list1)
        }
    }

    sealed class ViewModelListState{
        object Loading : ViewModelListState()
        data class Success(val list: List<Movie>): ViewModelListState()
        data class Error(val error: String): ViewModelListState()
    }
}