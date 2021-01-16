package ru.uniquenature.myapplication.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.uniquenature.myapplication.check.VerifyData
import ru.uniquenature.myapplication.check.VerifyResult
import ru.uniquenature.myapplication.data.Movie

class DetailsMovieViewModel(private val check: VerifyData):ViewModel() {
    private val KEY_MOVIE_DATA = "movieDetails"
    private val mutableDetailsMovie = MutableLiveData<ViewModelDetailsState>()

    val detailsMovieState: LiveData<ViewModelDetailsState> get() = mutableDetailsMovie

    fun loadingDetails(bundle:Bundle){
        val movie = bundle.getParcelable<Movie>(KEY_MOVIE_DATA)
        val newState = when(movie?.let { check.checkedMovie(movie) }){
            is VerifyResult.Error-> ViewModelDetailsState.Error("Error")
            else -> movie?.let { ViewModelDetailsState.Success(it) }
        }
        mutableDetailsMovie.value = newState
    }

    sealed class ViewModelDetailsState{
        data class Success(val movie: Movie): ViewModelDetailsState()
        data class Error(val error: String): ViewModelDetailsState()
    }
}