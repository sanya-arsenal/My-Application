package ru.uniquenature.myapplication.data

import androidx.room.*

@Dao
interface MoviesDao {
    @Transaction
    @Query("SELECT * FROM MoviesTableEntity")
    suspend fun getMovies():List<MovieWithActors>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovies(movie:List<MoviesTableEntity>)

    //@Query("SELECT * FROM ActorsTableEntity WHERE id_Movie")
    //suspend fun getActors(id:Long) : List<ActorsTableEntity>

    @Query("SELECT * FROM MoviesTableEntity WHERE id = :id_movie")
    suspend fun getMovie(id_movie:Long) : List<MovieWithActors>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActors(actor: List<ActorsTableEntity>)

    @Query("SELECT * FROM Genre")
    suspend fun getGenres() : List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGenres(genres: List<Genre>)
}