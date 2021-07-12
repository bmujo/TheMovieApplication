package com.samplecode.themovieapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.samplecode.themovieapp.R
import com.samplecode.themovieapp.fragments.MoviesFragment
import com.samplecode.themovieapp.fragments.ShowsFragment
import com.samplecode.themovieapp.viewmodels.BaseViewModel

class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment(R.layout.movies_fragment, BaseViewModel::class.java)
            1 -> ShowsFragment(R.layout.shows_fragment, BaseViewModel::class.java)
            else -> MoviesFragment(R.layout.movies_fragment, BaseViewModel::class.java)
        }
    }
}