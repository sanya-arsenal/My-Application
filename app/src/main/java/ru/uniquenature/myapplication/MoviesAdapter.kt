package ru.uniquenature.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private val clickListener:OnRecyclerItemClicked):RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.age?.text = movies[position].age
        holder.like?.setImageResource(movies[position].like)
        holder.imageMovie?.setImageResource(movies[position].movie_image)
        holder.genre?.text = movies[position].genre
        holder.rating?.rating = movies[position].rating.toFloat()
        holder.reviews?.text = movies[position].reviews
        holder.nameMovie?.text = movies[position].movie_name
        holder.duration?.text = movies[position].movie_duration

        holder.itemView.setOnClickListener {
            clickListener.onclick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies:List<Movie>){
        movies = newMovies
    }

    class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val age: TextView? = itemView.findViewById(R.id.tv_age)
        val like: ImageView? = itemView.findViewById(R.id.iv_like)
        val imageMovie: ImageView? = itemView.findViewById(R.id.iv_movie)
        val genre: TextView? = itemView.findViewById(R.id.tv_genre)
        val rating: RatingBar? = itemView.findViewById(R.id.rating_Bar_movie)
        val reviews: TextView? = itemView.findViewById(R.id.tv_reviews)
        val nameMovie: TextView? = itemView.findViewById(R.id.tv_movie_name)
        val duration: TextView? = itemView.findViewById(R.id.tv_min)
    }
}

interface OnRecyclerItemClicked{
    fun onclick(movie: Movie)
}