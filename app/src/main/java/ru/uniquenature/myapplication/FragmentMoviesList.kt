package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class FragmentMoviesList : Fragment() {
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_movie_list)
        recycler?.adapter = MoviesAdapter(MoviesDataSource.returnMovies())
        recycler?.layoutManager = GridLayoutManager(context,2)
    }

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

        fun doClick(item:Any) {
          /*  if (movie.movie_name == "Avengers: End Games"){
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentMoviesDetails())
                    .addToBackStack("FragmentMoviesDetails").commit()
            }*/
        }

    data class Movie(
        val age: String,
        val like: Int,
        val movie_image: Int,
        val genre: String,
        val rating: Double,
        val reviews: String,
        val movie_name: String,
        val movie_duration: String
    )
}