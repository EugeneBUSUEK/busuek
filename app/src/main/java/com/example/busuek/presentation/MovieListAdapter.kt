package com.example.busuek.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.busuek.R
import com.example.busuek.domain.Movie

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var moviesList = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onMovieLongClickListener: ((Movie) -> Unit)? = null
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_LIKED -> R.layout.movie_liked
            VIEW_TYPE_DISLIKED -> R.layout.movie_disliked
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        viewHolder.view.setOnLongClickListener {
            onMovieLongClickListener?.invoke(movie)
            true
        }
        viewHolder.view.setOnClickListener {
            onMovieClickListener?.invoke(movie)
        }
        viewHolder.tvName.text = movie.name
        viewHolder.tvCount.text = movie.id.toString()

    }

    override fun onViewRecycled(viewHolder: MovieViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.white))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        val movie = moviesList[position]
        return if (movie.liked) {
            VIEW_TYPE_LIKED
        } else {
            VIEW_TYPE_DISLIKED
        }
    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    interface OnMovieLongClickListener {

        fun onMovieLongClick(movie: Movie)
    }

    companion object {
        const val VIEW_TYPE_DISLIKED = 100
        const val VIEW_TYPE_LIKED = 101

        const val MAX_POOL_SIZE = 20
    }
}