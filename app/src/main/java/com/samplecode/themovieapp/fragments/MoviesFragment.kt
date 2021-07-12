package com.samplecode.themovieapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.samplecode.themovieapp.R
import com.samplecode.themovieapp.adapters.PagingMoviesRecyclerViewAdapter
import com.samplecode.themovieapp.models.Movie
import com.samplecode.themovieapp.viewmodels.BaseViewModel
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment<VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>?
) : Fragment(),
    PagingMoviesRecyclerViewAdapter.OnItemClickListener {

    private lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelClass?.let {
            viewModel = ViewModelProvider(requireActivity())
                .get(it)
        }

        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter2 = PagingMoviesRecyclerViewAdapter(this)

        moviesListRecyclerView.adapter = adapter2

        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            adapter2.submitData(viewLifecycleOwner.lifecycle, it)
            adapter2.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onItemClick(movie: Movie) {

        val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment2(
            movie.posterPath,
            movie.title,
            movie.overview
        )

        findNavController().navigate(action)
    }
}