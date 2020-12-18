package ru.uniquenature.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import ru.uniquenature.myapplication.data.Movie

class MoviesAdapter(private val movies: List<Movie>, private val adapterOnClick:(Int)->Unit):RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            age?.text = movies[position].minimumAge.toString()+"+"
            imageMovie?.let { Glide.with(itemView).load(movies[position].poster).into(it) }
            genre?.text = movies[position].genres.joinToString(separator = ", "){it.name}
            rating?.rating = movies[position].ratings/2
            reviews?.text = movies[position].numberOfRatings.toString() + " REVIEWS"
            nameMovie?.text = movies[position].title
            duration?.text = movies[position].runtime.toString()+ " MIN"

            itemView.setOnClickListener { adapterOnClick(position) }
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val age: TextView? = itemView.findViewById(R.id.tv_age)
        //val like: ImageView? = itemView.findViewById(R.id.iv_like)
        val imageMovie: ImageView? = itemView.findViewById(R.id.iv_movie)
        val genre: TextView? = itemView.findViewById(R.id.tv_genre)
        val rating: RatingBar? = itemView.findViewById(R.id.rating_Bar_movie)
        val reviews: TextView? = itemView.findViewById(R.id.tv_reviews)
        val nameMovie: TextView? = itemView.findViewById(R.id.tv_movie_name)
        val duration: TextView? = itemView.findViewById(R.id.tv_min)
    }
}