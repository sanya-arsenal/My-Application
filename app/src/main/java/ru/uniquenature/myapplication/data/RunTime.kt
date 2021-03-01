package ru.uniquenature.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RunTime(

    @SerialName("id")
    val id: Long,

    @SerialName("runtime")
    val runtime:Int
)
