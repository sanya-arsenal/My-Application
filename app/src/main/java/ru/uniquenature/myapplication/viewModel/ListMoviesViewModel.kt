package ru.uniquenature.myapplication.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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
import ru.uniquenature.myapplication.data.RepositoryMovies

class ListMoviesViewModel(private val check: VerifyData, private val repository:RepositoryMovies): ViewModel() {
    private val mutableListMovies = MutableLiveData<ViewModelListState>()

    val listMoviesState: LiveData<ViewModelListState> get() = mutableListMovies

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${viewModelScope.isActive}", throwable)
    }

    @ExperimentalSerializationApi
    fun loadingMovies(){
        viewModelScope.launch(exceptionHandler) {
            mutableListMovies.value = ViewModelListState.Loading
            val list = repository.loadMovies()
            //val list = loadMovies(context)
            val newState = when(check.checkedMovies(list)){
                is VerifyResult.Error-> ViewModelListState.Error("Loading error")
                is VerifyResult.Success-> ViewModelListState.Success(list)
            }
            mutableListMovies.value = newState
        }
    }

    sealed class ViewModelListState{
        object Loading : ViewModelListState()
        data class Success(val list: List<Movie>): ViewModelListState()
        data class Error(val error: String): ViewModelListState()
    }
}