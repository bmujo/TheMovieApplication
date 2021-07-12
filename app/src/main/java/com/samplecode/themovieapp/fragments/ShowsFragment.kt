package com.samplecode.themovieapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.samplecode.themovieapp.R
import com.samplecode.themovieapp.adapters.PagingTvShowsRecyclerViewAdapter
import com.samplecode.themovieapp.models.TvShow
import com.samplecode.themovieapp.viewmodels.BaseViewModel
import kotlinx.android.synthetic.main.shows_fragment.*

class ShowsFragment<VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>?
) : Fragment(),
    PagingTvShowsRecyclerViewAdapter.OnItemClickListener {

    private lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelClass?.let {
            viewModel = ViewModelProvider(requireActivity())
                .get(it)
        }

        return inflater.inflate(R.layout.shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PagingTvShowsRecyclerViewAdapter(this)

        tvShowsListRecyclerView.adapter = adapter

        viewModel.listOfTvShows.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(tvShow: TvShow) {
        val action = MainFragmentDirections.actionMainFragmentToShowDetailsFragment2(tvShow.posterPath!!, tvShow.name, tvShow.overview)

        findNavController().navigate(action)
    }
}