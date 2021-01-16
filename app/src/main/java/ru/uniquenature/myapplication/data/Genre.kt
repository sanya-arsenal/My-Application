package ru.uniquenature.myapplication.data
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