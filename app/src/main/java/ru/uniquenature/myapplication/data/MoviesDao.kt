package ru.uniquenature.myapplication.data

import androidx.room.*

@Dao
interface MoviesDao {
    @Transaction
    @Query("SELECT * FROM MoviesTableEntity")
    suspend fun getMovies():List<MovieWithActorsGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovies(movie:List<MoviesTableEntity>)

    @Transaction
    @Query("SELECT * FROM MoviesTableEntity WHERE id = :id_movie")
    suspend fun getMovie(id_movie:Long) : List<MovieWithActorsGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActors(actor: List<ActorsTableEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGenres(genres: List<GenreTableEntity>)
}