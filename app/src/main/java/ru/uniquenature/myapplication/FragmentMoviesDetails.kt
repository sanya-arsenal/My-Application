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
            recyclerActor?.adapter = ActorsAdapter()
            recyclerActor?.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
    }

    override fun onStart() {
        super.onStart()
        (recyclerActor?.adapter as? ActorsAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
        }
    }

}

