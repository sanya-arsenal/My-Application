package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesDetails : Fragment() {
    private var recyclerActor: RecyclerView? = null
    private var backFragment: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details,container,false)
        backFragment = view.findViewById(R.id.tv_back)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            backFragment?.setOnClickListener{
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FragmentMoviesList()).commit()
            }
            recyclerActor = view.findViewById(R.id.rv_actors_list)
            recyclerActor?.adapter = ActorsAdapter(ActorsDataSource.returnActors())
            recyclerActor?.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
    }

    object ActorsDataSource{
        private val actors = listOf(
            Actor(R.drawable.robert,"Robert Downey Jr."),
            Actor(R.drawable.chris_e,"Chris Evans"),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.chris_h,"Chris Hemsworth"),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.robert,"Robert Downey Jr."),
            Actor(R.drawable.mark,"Mark Ruffalo"),
            Actor(R.drawable.robert,"Robert Downey Jr.")
        )

        fun returnActors(): List<Actor>{
            return actors
        }
    }

    data class Actor(
        val photoActor: Int,
        val nameActor: String
    )
}

