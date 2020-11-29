package ru.uniquenature.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
    private var clReplaceFragment: ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list,container,false)
        clReplaceFragment = view.findViewById(R.id.constraint_layout_image)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clReplaceFragment?.setOnClickListener {
            activity?.let {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container,FragmentMoviesDetails())
                    .addToBackStack("FragmentMoviesDetails").commit()
            }
        }
    }

}