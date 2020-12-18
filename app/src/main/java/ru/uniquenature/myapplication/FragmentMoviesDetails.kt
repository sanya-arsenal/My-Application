package ru.uniquenature.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.uniquenature.myapplication.data.Movie
import ru.uniquenature.myapplication.data.loadMovies

class FragmentMoviesDetails(private val position: Int) : Fragment() {
    private var recyclerActor: RecyclerView? = null
    private var backFragment: TextView? = null
    private var age: TextView? = null
    private var image: ImageView? = null
    private var title: TextView? = null
    private var genres: TextView? = null
    private var rating: RatingBar? = null
    private var reviews: TextView? = null
    private var overView: TextView? = null

    //private var movies: List<Movie> = listOf()
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        backFragment = view.findViewById(R.id.tv_back)
        age = view.findViewById(R.id.tv_13)
        image = view.findViewById(R.id.iv_avengers)
        title = view.findViewById(R.id.tv_avengers_and_games)
        genres = view.findViewById(R.id.tv_action_adventure)
        rating = view.findViewById(R.id.rating_Bar)
        reviews = view.findViewById(R.id.tv_reviews)
        overView = view.findViewById(R.id.tv_after_the_devastating)
        recyclerActor = view.findViewById(R.id.rv_actors_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backFragment?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, FragmentMoviesList()).commit()
        }
        scope.launch {
            context?.let {
                showResult(loadMovies(it))
            }
        }
        recyclerActor?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(movies: List<Movie>) {
        age?.text = movies[position].minimumAge.toString() + "+"
        title?.text = movies[position].title
        image?.let { Glide.with(this).load(movies[position].backdrop).into(it) }
        genres?.text = movies[position].genres.joinToString(separator = ", ") { it.name }
        rating?.rating = movies[position].ratings / 2
        reviews?.text = movies[position].numberOfRatings.toString() + " REVIEWS"
        overView?.text = movies[position].overview
        recyclerActor?.adapter = ActorsAdapter(movies[position].actors)
    }
}

