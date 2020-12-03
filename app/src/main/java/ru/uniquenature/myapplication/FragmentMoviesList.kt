package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment() {
    var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_movie_list)
        recycler?.adapter = MoviesAdapter(clickListener)
        recycler?.layoutManager = GridLayoutManager(context,2)
    }

    override fun onStart() {
        super.onStart()
        (recycler?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

    private val clickListener = object:OnRecyclerItemClicked{
        override fun onclick(movie: Movie) {
            if (movie.movie_name=="Avengers: End Games"){
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentMoviesDetails())
                    .addToBackStack("FragmentMoviesDetails").commit()
            }

        }
    }

}