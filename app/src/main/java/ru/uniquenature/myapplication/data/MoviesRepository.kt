package ru.uniquenature.myapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import java.lang.IllegalArgumentException

class MoviesRepository {
    private val dataStore = RemoteDataStore

    @ExperimentalSerializationApi
    suspend fun loadMovies():List<Movie> {
        val genres: List<Genre> = loadGenres()
        val genresMap = genres.associateBy { it.id }
        return RemoteDataStore.getPopularMovies().map { moviesResult->
            Movie(
                id = moviesResult.id,
                title = moviesResult.originalTitle,
                overview = moviesResult.overview,
                poster = MoviesAPI.BASE_URL1+moviesResult.posterPath,
                backdrop = MoviesAPI.BASE_URL1+moviesResult.backdropPath,
                ratings = moviesResult.voteAverage,
                numberOfRatings = moviesResult.voteCount,
                minimumAge = if (moviesResult.adult) 16 else 13,
                runtime =  loadRunTime(moviesResult.id),
                genres = moviesResult.genreIDS.map { genresMap[it] ?: throw IllegalArgumentException("Genre not found") },
                actors = loadActors(moviesResult.id)
            )
        }
    }

    @ExperimentalSerializationApi
    suspend fun loadGenres():List<Genre> = withContext(Dispatchers.IO){
        dataStore.getGenres()
    }

    @ExperimentalSerializationApi
    suspend fun loadActors(id: Long):List<Actor> = withContext(Dispatchers.IO){
         dataStore.getActors(id).map {actor ->
            Actor(
                    id = actor.id,
                    name = actor.name,
                    picture = MoviesAPI.BASE_URL1+actor.picture
            )
        }
    }

    @ExperimentalSerializationApi
    suspend fun loadRunTime(id: Long): Int = withContext((Dispatchers.IO)){
        RemoteDataStore.getRuntime(id)
    }
}