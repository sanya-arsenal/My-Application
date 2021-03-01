package ru.uniquenature.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.serialization.ExperimentalSerializationApi
import ru.uniquenature.myapplication.R
import ru.uniquenature.myapplication.listViewModel.ListMoviesViewModel
import ru.uniquenature.myapplication.listViewModel.ListViewModelFactory
import ru.uniquenature.myapplication.data.Movie

class FragmentMoviesList : Fragment() {
    private val viewModel: ListMoviesViewModel by viewModels { ListViewModelFactory(applicationContext = requireContext().applicationContext) }

    private var recycler: RecyclerView? = null
    private var progress: View? = null

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list,container,false)

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewModel.loadingMovies()
        viewModel.listMoviesState.observe(this.viewLifecycleOwner,this::setState)
    }

    private fun setState(state: ListMoviesViewModel.ViewModelListState)= when (state){
        is ListMoviesViewModel.ViewModelListState.Success->showMovies(state.list)
        is ListMoviesViewModel.ViewModelListState.Error->showError(state.error)
        is ListMoviesViewModel.ViewModelListState.Loading->showProgress()
    }

    private fun showProgress(){
        progress?.isVisible = true
    }

    private fun showMovies(movies:List<Movie>){
        recycler?.adapter = MoviesAdapter(movies){item -> doClick(item.id)}
        progress?.isVisible = false
    }

    @SuppressLint("ShowToast")
    private fun showError(error:String){
        recycler?.isVisible = false
        progress?.isVisible = false
        Toast.makeText(activity,error,Toast.LENGTH_LONG).show()
    }

    private fun initViews(view:View){
        progress = view.findViewById(R.id.progressStatus)
        recycler = view.findViewById(R.id.rv_movie_list)
        recycler?.layoutManager = GridLayoutManager(context, 2)
    }


    private fun doClick(id_movie:Long) {
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.main_container, FragmentMoviesDetails.newInstance(id_movie))
             .addToBackStack("FragmentMoviesDetails").commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}