package ru.uniquenature.myapplication.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val picture: String?
)

@Serializable
data class ResultActors (
    val id: Long,
    val cast: List<Actor>,
    val crew: List<Actor>,
)

@Entity(foreignKeys = [ForeignKey(
                entity = MoviesTableEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("id_Movie"),
                onDelete = ForeignKey.CASCADE,
        )],
        primaryKeys = ["id", "id_Movie"]
)
data class ActorsTableEntity(
        val id_Movie:Long,
        val id: Long,
        val name: String,
        val picture: String?
)
