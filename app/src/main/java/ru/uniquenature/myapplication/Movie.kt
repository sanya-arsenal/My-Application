package ru.uniquenature.myapplication

data class Movie(
    val age: String,
    val like: Int,
    val movie_image: Int,
    val genre: String,
    val rating: Double,
    val reviews: String,
    val movie_name: String,
    val movie_duration: String)

object MoviesDataSource{
    private val movies = listOf(
        Movie("13+", R.drawable.like,R.drawable.avengers_and_games,"Action, Adventure, Drama",4.0,"125 REVIEWS","Avengers: End Games","137 MIN"),
        Movie("16+", R.drawable.like,R.drawable.telnet,"Action, Sci-Fi, Thriller",5.0,"98 REVIEWS","Telnet","97 MIN"),
        Movie("13+", R.drawable.like,R.drawable.black_widow,"Action, Adventure, Sci-Fi",4.0,"38 REVIEWS","Black Widow","102 MIN"),
        Movie("13+", R.drawable.like,R.drawable.wonder_woman,"Action, Adventure, Fantasy",5.0,"74 REVIEWS","Wonder Woman","120 MIN")
    )

    fun returnMovies(): List<Movie>{
        return movies
    }
}
