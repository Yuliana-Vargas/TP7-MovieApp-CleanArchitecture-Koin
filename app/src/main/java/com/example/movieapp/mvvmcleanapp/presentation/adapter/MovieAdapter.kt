package com.example.movieapp.mvvmcleanapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemRecyclerBinding
import com.example.movieapp.mvvmcleanapp.domain.entity.MovieData

class MovieAdapter(private val movies: List<MovieData>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecyclerBinding.bind(itemView)

        fun bind(movie: MovieData) {
            binding.id.text = itemView.context.getString(R.string.card_id, movie.id.toString())
            binding.title.text = itemView.context.getString(R.string.card_title, movie.title)
            binding.voteAverage.text = itemView.context.getString(R.string.card_vote_average, movie.voteAverage.toString())

            Glide.with(itemView.context).load(itemView.context.getString(R.string.card_poster, movie.posterPath)).into(binding.poster)
        }
    }
}
