package ru.uniquenature.myapplication.data

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

class MoviesRepository(private val dataStore : RemoteDataStore, context: Context ){
    private val instanceDataBase = MoviesDataBase.create(context)

    @ExperimentalSerializationApi
    suspend fun loadMovies():List<Movie> {
        val genres: List<Genre> = loadGenres()
        val genresMap = genres.associateBy { it.id }
        return dataStore.getPopularMovies().map { moviesResult->
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
        dataStore.getRuntime(id)
    }

    private fun convertMovieToMovieDB(movies: List<Movie>) : List<MoviesTableEntity> {
        return movies.map { movie->
            MoviesTableEntity(
                    id = movie.id,
                    originalTitle = movie.title,
                    overview = movie.overview,
                    posterPath = movie.poster,
                    backdropPath = movie.backdrop,
                    voteAverage = movie.ratings,
                    voteCount = movie.numberOfRatings,
                    adult = movie.minimumAge.toLong(),
                    runTime = movie.runtime.toLong(),
                    genreIDS = movie.genres.map { it.id }
            )
        }
    }

    private fun convertMovieToMovieScreen(movies: List<MovieWithActorsGenres>): List<Movie>{
        return movies.map { movie->
            Movie(
                    id = movie.moviesTableEntity.id,
                    title = movie.moviesTableEntity.originalTitle,
                    overview = movie.moviesTableEntity.overview,
                    poster = movie.moviesTableEntity.posterPath,
                    backdrop = movie.moviesTableEntity.backdropPath,
                    ratings = movie.moviesTableEntity.voteAverage,
                    numberOfRatings = movie.moviesTableEntity.voteCount,
                    minimumAge = movie.moviesTableEntity.adult.toInt(),
                    runtime = movie.moviesTableEntity.runTime.toInt(),
                    genres = movie.genres,
                    actors = movie.actors
            )
        }
    }

    private fun convertMovieToActorsDB(idMovie:Long, actors: List<Actor>) : List<ActorsTableEntity>{
        return actors.map { movie->
            ActorsTableEntity(
                    id_Movie = idMovie,
                    id = movie.id,
                    name = movie.name,
                    picture = movie.picture
            )
        }
    }

    private fun convertMovieToGenresDB(idMovie:Long, genres: List<Genre>) : List<GenreTableEntity>{
        return genres.map { movie->
            GenreTableEntity(
                    id_Movie = idMovie,
                    id = movie.id,
                    name = movie.name,
            )
        }
    }

    suspend fun getMovieForDB(id: Long) : Movie{
        return withContext(Dispatchers.IO){
            convertMovieToMovieScreen(instanceDataBase.moviesDao.getMovie(id_movie = id))[0]
        }
    }

    suspend fun getMoviesForDB() : List<Movie> {
        return withContext(Dispatchers.IO) {
            convertMovieToMovieScreen(instanceDataBase.moviesDao.getMovies())
        }
    }

    suspend fun saveMoviesDB(movies:List<Movie>){
        withContext(Dispatchers.IO){
            instanceDataBase.moviesDao.updateMovies(convertMovieToMovieDB(movies = movies))
            movies.forEach {
                instanceDataBase.moviesDao.updateActors(convertMovieToActorsDB(it.id,it.actors))
                instanceDataBase.moviesDao.updateGenres(convertMovieToGenresDB(it.id,it.genres))
                Log.v("saveMovie"," ${it.id},${it.actors},${it.genres}")
            }
        }
    }
}