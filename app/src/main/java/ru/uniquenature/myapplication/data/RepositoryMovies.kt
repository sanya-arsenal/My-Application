package ru.uniquenature.myapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import java.lang.IllegalArgumentException

class RepositoryMovies {
    @ExperimentalSerializationApi
    suspend fun loadMovies():List<Movie> {
        val genres: List<Genre> = loadGenres()
        val genresMap = genres.associateBy { it.id }
        return RemoteDataStore.moviesAPI.getPopularMovies().results.map { MoviesResult->
            Movie(
                id = MoviesResult.id,
                title = MoviesResult.originalTitle,
                overview = MoviesResult.overview,
                poster = MoviesAPI.BASE_URL1+MoviesResult.posterPath,
                backdrop = MoviesAPI.BASE_URL1+MoviesResult.backdropPath,
                ratings = MoviesResult.voteAverage,
                numberOfRatings = MoviesResult.voteCount,
                minimumAge = if (MoviesResult.adult) 16 else 13,
                runtime =  loadRunTime(MoviesResult.id),
                genres = MoviesResult.genreIDS.map { genresMap[it] ?: throw IllegalArgumentException("Genre not found") },
                actors = loadActors(MoviesResult.id)
            )
        }
    }

    @ExperimentalSerializationApi
    suspend fun loadGenres():List<Genre> = withContext(Dispatchers.IO){
        RemoteDataStore.moviesAPI.getGenres().genres
    }

    @ExperimentalSerializationApi
    suspend fun loadActors(id: Long):List<Actor> = withContext(Dispatchers.IO){
        return@withContext RemoteDataStore.moviesAPI.getActors(id).cast.map { Actor->
            Actor(
                    id = Actor.id,
                    name = Actor.name,
                    picture = MoviesAPI.BASE_URL1+Actor.picture
            )
        }
    }

    @ExperimentalSerializationApi
    suspend fun loadRunTime(id: Long): Int = withContext((Dispatchers.IO)){
        RemoteDataStore.moviesAPI.getRunTime(id).runtime
    }
}