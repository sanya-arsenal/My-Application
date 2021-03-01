package ru.uniquenature.myapplication.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toListOfString(flatStringList : String) : List<Long>{
        return flatStringList.split(",").map { it.toLong() }
    }

    @TypeConverter
    fun fromListOfString(listOfString : List<Long>) : String{
        return listOfString.joinToString(",")
    }

}

