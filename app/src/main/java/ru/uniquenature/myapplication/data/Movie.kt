package ru.uniquenature.myapplication.data

import android.os.Parcel
import android.os.Parcelable

@Suppress("UNREACHABLE_CODE")
data class Movie(
        val id: Int,
        val title: String?,
        val overview: String?,
        val poster: String?,
        val backdrop: String?,
        val ratings: Float,
        val numberOfRatings: Int,
        val minimumAge: Int,
        val runtime: Int,
        val genres: List<Genre>,
        val actors: List<Actor>
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            genres = TODO("genres"),
            actors = TODO("actors")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(poster)
        parcel.writeString(backdrop)
        parcel.writeFloat(ratings)
        parcel.writeInt(numberOfRatings)
        parcel.writeInt(minimumAge)
        parcel.writeInt(runtime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}