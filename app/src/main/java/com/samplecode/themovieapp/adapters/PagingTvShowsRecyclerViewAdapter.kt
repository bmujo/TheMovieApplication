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
import com.samplecode.themovieapp.models.TvShow

class PagingTvShowsRecyclerViewAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<TvShow, PagingTvShowsRecyclerViewAdapter.TvShowItemViewHolder>(ITEM_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowItemViewHolder {
        val binding = ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TvShowItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowItemViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    inner class TvShowItemViewHolder(private val binding: ItemCellBinding) :
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

        fun bind(tvShow: TvShow){
            binding.apply {
                Glide.with(itemView)
                    .load(ApiClientInterface.BASE_IMAGE_URL + tvShow.posterPath)
                    .placeholder(R.drawable.no_poster)
                    .into(binding.moviePosterImageView)

                binding.titleTextView.text = tvShow.name
                binding.descriptionTextView.text = if(tvShow.overview.length > 50) "${tvShow.overview.subSequence(0,50)} ..." else tvShow.overview

                binding.orderNumberTextView.text = (position + 1).toString()
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(tvShow: TvShow)
    }

    companion object{
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<TvShow>(){
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}