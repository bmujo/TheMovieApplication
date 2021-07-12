package com.samplecode.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samplecode.themovieapp.R
import com.samplecode.themovieapp.api.ApiClientInterface
import com.samplecode.themovieapp.databinding.ItemCellBinding
import com.samplecode.themovieapp.models.Movie

class PagingMoviesRecyclerViewAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Movie, PagingMoviesRecyclerViewAdapter.MovieItemViewHolder>(ITEM_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    inner class MovieItemViewHolder(private val binding: ItemCellBinding) :
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null)
                    {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(movie: Movie){
            binding.apply {
                Glide.with(itemView)
                    .load(ApiClientInterface.BASE_IMAGE_URL + movie.posterPath)
                    .placeholder(R.drawable.no_poster)
                    .into(binding.moviePosterImageView)

                binding.titleTextView.text = movie.title
                binding.descriptionTextView.text = "${movie.overview.subSequence(0,50)} ..."

                binding.orderNumberTextView.text = (position + 1).toString()
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(movie: Movie)
    }

    companion object{
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}