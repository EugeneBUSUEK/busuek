package com.example.busuek.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.busuek.R
import com.example.busuek.domain.Movie

class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

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
        val movie = getItem(position)
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

    override fun getItemViewType(position: Int): Int {
        val movie = getItem(position)
        return if (movie.liked) {
            VIEW_TYPE_LIKED
        } else {
            VIEW_TYPE_DISLIKED
        }
    }

    companion object {
        const val VIEW_TYPE_DISLIKED = 100
        const val VIEW_TYPE_LIKED = 101

        const val MAX_POOL_SIZE = 20
    }
}