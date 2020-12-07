package ru.uniquenature.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private val movies:List<Movie>, private val adapterOnClick:(String)->Unit):RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            age?.text = movies[position].age
            like?.setImageResource(movies[position].like)
            imageMovie?.setImageResource(movies[position].movie_image)
            genre?.text = movies[position].genre
            rating?.rating = movies[position].rating.toFloat()
            reviews?.text = movies[position].reviews
            nameMovie?.text = movies[position].movie_name
            duration?.text = movies[position].movie_duration

            itemView.setOnClickListener { adapterOnClick(movies[position].movie_name) }
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
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