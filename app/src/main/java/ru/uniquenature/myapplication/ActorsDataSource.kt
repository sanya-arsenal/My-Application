package ru.uniquenature.myapplication

class ActorsDataSource {
    fun getActors():List<Actor>{
        return listOf(
            Actor(R.drawable.robert,"Robert Downey Jr."),
            Actor(R.drawable.chris_e,"Chris Evans"),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.chris_h,"Chris Hemsworth"),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.robert,"Robert Downey Jr."),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.robert,"Robert Downey Jr.")
        )
    }
}