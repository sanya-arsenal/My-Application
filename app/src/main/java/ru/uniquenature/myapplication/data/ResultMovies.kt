package ru.uniquenature.myapplication.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result (
        @SerialName("page")
        val page: Long,

        @SerialName("results")
        val results: List<Movies>,

        @SerialName("total_pages")
        val totalPages: Long,

        @SerialName("total_results")
        val totalResults: Long
)

@Serializable
data class Movies (
        @SerialName("adult")
        val adult: Boolean,

        @SerialName("backdrop_path")
        val backdropPath: String,

        @SerialName("genre_ids")
        val genreIDS: List<Long>,

        @PrimaryKey
        @SerialName("id")
        val id: Long,

        @SerialName("original_title")
        val originalTitle: String,

        @SerialName("overview")
        val overview: String,

        @SerialName("poster_path")
        val posterPath: String,

        @SerialName("vote_average")
        val voteAverage: Double,

        @SerialName("vote_count")
        val voteCount: Long
)

@Entity
data class MoviesTableEntity (
        @PrimaryKey(autoGenerate = false)
        val id: Long,
        val adult: Long,
        val backdropPath: String?,
        val genreIDS: List<Long>,
        val originalTitle: String?,
        val overview: String?,
        val posterPath: String?,
        val runTime: Long,
        val voteAverage: Double,
        val voteCount: Long
)

data class MovieWithActorsGenres(
        @Embedded val moviesTableEntity: MoviesTableEntity,
        @Relation(
                parentColumn = "id",
                entityColumn = "id_Movie",
                entity = ActorsTableEntity::class
        )
        val actors: List<Actor>,
        @Relation(
                parentColumn = "id",
                entityColumn = "id_Movie",
                entity = GenreTableEntity::class
        )
        val genres: List<Genre>
)