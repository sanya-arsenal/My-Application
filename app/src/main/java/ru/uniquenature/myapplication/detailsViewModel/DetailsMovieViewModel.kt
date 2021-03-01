package ru.uniquenature.myapplication.detailsViewModel

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.check.VerifyResult
import ru.uniquenature.myapplication.data.Movie
import ru.uniquenature.myapplication.data.MoviesRepository

class DetailsMovieViewModel(private val check: VerifyData, private val repository: MoviesRepository):ViewModel() {
    private  val KEY_ID_MOVIE = "id_Movie"
    private val mutableDetailsMovie = MutableLiveData<ViewModelDetailsState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(ContentValues.TAG, "Coroutine exception, scope active:${viewModelScope.isActive}", throwable)
    }

    val detailsMovieState: LiveData<ViewModelDetailsState> get() = mutableDetailsMovie

    fun loadingDetails(bundle:Bundle) {
        viewModelScope.launch(exceptionHandler) {
            val id = bundle.getLong(KEY_ID_MOVIE)
            val movie = repository.getMovieForDB(id)
            val newState = when (movie.let { check.checkedMovie(movie) }) {
                is VerifyResult.Error -> ViewModelDetailsState.Error("Error")
                else -> movie.let { ViewModelDetailsState.Success(it) }
            }
            mutableDetailsMovie.value = newState
        }
    }

    sealed class ViewModelDetailsState{
        data class Success(val movie: Movie): ViewModelDetailsState()
        data class Error(val error: String): ViewModelDetailsState()
    }
}