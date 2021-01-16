package ru.uniquenature.myapplication.data

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

@Serializable
data class RunTime (
        @SerialName("id")
        val id: Long,

        @SerialName("runtime")
        val runtime:Int
)