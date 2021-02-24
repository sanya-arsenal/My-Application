package ru.uniquenature.myapplication.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListGenres (
    @SerialName("genres")
    val genres: List<Genre>
)

@Entity
@Serializable
data class Genre(
        @PrimaryKey(autoGenerate = false)
        @SerialName("id")
        val id: Long,
        @SerialName("name")
        val name: String
)