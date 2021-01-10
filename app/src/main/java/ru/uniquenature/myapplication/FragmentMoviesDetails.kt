package ru.uniquenature.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.uniquenature.myapplication.data.Movie

class FragmentMoviesDetails : Fragment() {
    private val viewModel: DetailsMovieViewModel by viewModels { DetailsViewModelFactory() }

    private var movie: Movie? = null
    private var recyclerActor: RecyclerView? = null
    private var backFragment: TextView? = null
    private var age: TextView? = null
    private var image: ImageView? = null
    private var title: TextView? = null
    private var genres: TextView? = null
    private var rating: RatingBar? = null
    private var reviews: TextView? = null
    private var overView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        backFragment()
        arguments?.let {
            viewModel.loadingDetails(it)
        }
        viewModel.detailsMovieState.observe(this.viewLifecycleOwner,this::setState)
    }

    private fun setState(state:DetailsMovieViewModel.ViewModelDetailsState) {
        when(state){
            is DetailsMovieViewModel.ViewModelDetailsState.Error->showError(state.error)
            is DetailsMovieViewModel.ViewModelDetailsState.Success->showResult(state.movie)
        }
    }

    private fun backFragment(){
        backFragment?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FragmentMoviesList()).commit()
        }
    }

    private fun showError(error:String){
        Toast.makeText(activity,error, Toast.LENGTH_LONG).show()
    }

    private fun initViews(view: View){
        backFragment = view.findViewById(R.id.tv_back)
        age = view.findViewById(R.id.tv_13)
        image = view.findViewById(R.id.iv_avengers)
        title = view.findViewById(R.id.tv_avengers_and_games)
        genres = view.findViewById(R.id.tv_action_adventure)
        rating = view.findViewById(R.id.rating_Bar)
        reviews = view.findViewById(R.id.tv_reviews)
        overView = view.findViewById(R.id.tv_after_the_devastating)
        recyclerActor = view.findViewById(R.id.rv_actors_list)
        recyclerActor?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(movie: Movie) {
        age?.text = movie.minimumAge.toString() + "+"
        title?.text = movie.title
        image?.let { Glide.with(this).load(movie.backdrop).into(it) }
        genres?.text = movie.genres.joinToString(separator = ", ") { it.name }
        rating?.rating = movie.ratings / 2
        reviews?.text = movie.numberOfRatings.toString() + " REVIEWS"
        overView?.text = movie.overview
        recyclerActor?.adapter = ActorsAdapter(movie.actors)
    }

    companion object{
        private const val KEY_MOVIE_DATA = "movieDetails"
        fun newInstance(movie:Movie) = FragmentMoviesDetails().apply{
                arguments = Bundle().apply {
                    putParcelable(KEY_MOVIE_DATA, movie)
            }
        }
    }
}