package ru.uniquenature.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter:RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {
    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor,parent,false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.photo?.setImageResource(actors[position].photoActor)
        holder.name?.text = actors[position].nameActor
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors:List<Actor>){
        actors = newActors
    }

    class ActorViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val photo: ImageView? = itemView.findViewById(R.id.iv_photo)
        val name: TextView? = itemView.findViewById(R.id.tv_name)
    }
}