package ru.uniquenature.myapplication.data

import android.content.Context
import androidx.room.*

@Database(entities = [MoviesTableEntity::class, Genre::class, ActorsTableEntity::class],version = 3,exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoviesDataBase : RoomDatabase(){
    abstract val moviesDao : MoviesDao

    companion object{
        fun create(context: Context) = Room.databaseBuilder(
                context,
                MoviesDataBase::class.java,
                "moviesDB"
        )
                .fallbackToDestructiveMigration()
                .build()
    }
}