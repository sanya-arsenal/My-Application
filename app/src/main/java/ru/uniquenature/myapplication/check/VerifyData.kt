package ru.uniquenature.myapplication.check

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.uniquenature.myapplication.data.Movie

class VerifyData(private val dispatcher: CoroutineDispatcher){

    suspend fun checkedMovies(movies:List<Movie>): VerifyResult = withContext(dispatcher){
        when{
            movies.isEmpty()->VerifyResult.Error
            else -> VerifyResult.Success
        }
    }

    fun checkedMovie(movie:Movie?): VerifyResult = when(movie){
            null->VerifyResult.Error
            else->VerifyResult.Success
    }
}