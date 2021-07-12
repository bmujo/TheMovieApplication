package com.samplecode.themovieapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.samplecode.themovieapp.R
import com.samplecode.themovieapp.api.ApiClientInterface
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {
    val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(ApiClientInterface.BASE_IMAGE_URL + args.imagePosterPath)
            .placeholder(R.drawable.no_poster)
            .into(moviePosterImageView)

        movieTitleTextView.text = args.movieTitle
        movieDescriptionTextView.text = args.movieDescription
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val item: MenuItem? = menu.findItem(R.id.search_action)
        if (item != null) {
            item.collapseActionView()
            item.isVisible = false
        }
    }
}