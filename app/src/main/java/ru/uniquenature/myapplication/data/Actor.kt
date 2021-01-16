package ru.uniquenature.myapplication.data

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
    val crew: List<Actor>
)
