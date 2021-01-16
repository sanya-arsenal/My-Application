package ru.uniquenature.myapplication.data

import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPI {
    @GET("movie/popular?api_key=bcd98dd00c8ea8bfc7b0a4f4e1ff6d5e&language=en-US&page=1")
    suspend fun getPopularMovies(): Result

    @GET("genre/movie/list?api_key=bcd98dd00c8ea8bfc7b0a4f4e1ff6d5e&language=en-US")
    suspend fun getGenres(): ListGenres

    @GET("movie/{movie_id}?api_key=bcd98dd00c8ea8bfc7b0a4f4e1ff6d5e&language=en-US")
    suspend fun getRunTime(@Path("movie_id") movieID:Long): RunTime

    @GET("movie/{movie_id}/credits?api_key=bcd98dd00c8ea8bfc7b0a4f4e1ff6d5e&language=ru-ru")
    suspend fun getActors(@Path("movie_id") movieID:Long): ResultActors

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL1 = "https://image.tmdb.org/t/p/original"
        const val KEY_API = "bcd98dd00c8ea8bfc7b0a4f4e1ff6d5e"
    }
}