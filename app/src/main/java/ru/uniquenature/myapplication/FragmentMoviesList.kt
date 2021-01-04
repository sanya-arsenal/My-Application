package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.uniquenature.myapplication.data.Movie
import ru.uniquenature.myapplication.data.loadMovies

class FragmentMoviesList : Fragment() {
    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_movie_list)
        recycler?.layoutManager = GridLayoutManager(context, 2)
        scope.launch {
            context?.let {
                recycler?.adapter = MoviesAdapter(loadMovies(it)) { item -> doClick(item) }
            }
        }
    }

    private fun doClick(movie:Movie) {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentMoviesDetails.newInstance(movie))
                .addToBackStack("FragmentMoviesDetails").commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}