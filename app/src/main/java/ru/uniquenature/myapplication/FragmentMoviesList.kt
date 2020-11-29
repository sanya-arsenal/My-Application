package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
    private var imReplaceFragment: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list,container,false)
        imReplaceFragment = view.findViewById(R.id.iv_movie)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imReplaceFragment?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container,FragmentMoviesDetails())
                ?.addToBackStack("FragmentMoviesDetails")?.commit()
        }
    }

}