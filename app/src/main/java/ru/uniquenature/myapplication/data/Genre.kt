package ru.uniquenature.myapplication.data
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListGenres (
    @SerialName("genres")
    val genres: List<Genre>
)

@Serializable
data class Genre(
        @SerialName("id")
        val id: Long,
        @SerialName("name")
        val name: String
)

@Entity(foreignKeys = [ForeignKey(
        entity = MoviesTableEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_Movie"),
        onDelete = ForeignKey.CASCADE,
)],
        primaryKeys = ["id", "id_Movie"]
)
data class GenreTableEntity(
        val id_Movie:Long,
        val id: Long,
        val name: String
)
