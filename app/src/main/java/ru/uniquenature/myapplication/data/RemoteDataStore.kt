package ru.uniquenature.myapplication.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RemoteDataStore {
    const val API_KEY_HEADER = "api-key"
    private val json = Json { ignoreUnknownKeys = true }
    val moviesRepository = MoviesRepository()

    private class MoviesHeaderInterceptors : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val originRequest = chain.request()
            val originHttpUrl = originRequest.url

            val newRequest = originRequest.newBuilder()
                .url(originHttpUrl)
                .addHeader(API_KEY_HEADER, MoviesAPI.KEY_API)
                .build()

            return chain.proceed(newRequest)
        }
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(MoviesHeaderInterceptors())
        .build()

    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(MoviesAPI.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @ExperimentalSerializationApi
    val moviesAPI:MoviesAPI = retrofit.create(MoviesAPI::class.java)

    @ExperimentalSerializationApi
    suspend fun getGenres():List<Genre>{
        return moviesAPI.getGenres().genres
    }

    @ExperimentalSerializationApi
    suspend fun getRuntime(movie_id:Long):Int{
        return moviesAPI.getRunTime(movie_id).runtime
    }

    @ExperimentalSerializationApi
    suspend fun getPopularMovies():List<Movies>{
        return moviesAPI.getPopularMovies().results
    }

    @ExperimentalSerializationApi
    suspend fun getActors(movie_id:Long):List<Actor>{
        return moviesAPI.getActors(movie_id).cast
    }
}