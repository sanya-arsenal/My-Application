package ru.uniquenature.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.uniquenature.myapplication.R
import ru.uniquenature.myapplication.data.Actor
import ru.uniquenature.myapplication.data.MoviesAPI

class ActorsAdapter(private var actors: List<Actor>):RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor,parent,false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.apply {
            photo?.let { Glide.with(itemView).load(actors[position].picture).into(it) }
            //photo?.setImageResource(actors[position].photoActor)
            name?.text = actors[position].name
        }
    }

    override fun getItemCount(): Int = actors.size

    class ActorViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val photo: ImageView? = itemView.findViewById(R.id.iv_photo)
        val name: TextView? = itemView.findViewById(R.id.tv_name)
    }
}