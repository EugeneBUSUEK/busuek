package com.example.busuek.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.busuek.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movieList.observe(this) {
            movieListAdapter.moviesList = it
        }
    }

    private fun setupRecyclerView() {
        val rvMoviesList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvMoviesList) {
            movieListAdapter = MovieListAdapter()
            rvMoviesList.adapter = movieListAdapter
            rvMoviesList.recycledViewPool.setMaxRecycledViews(
                MovieListAdapter.VIEW_TYPE_DISLIKED,
                MovieListAdapter.MAX_POOL_SIZE
            )
            rvMoviesList.recycledViewPool.setMaxRecycledViews(
                MovieListAdapter.VIEW_TYPE_LIKED,
                MovieListAdapter.MAX_POOL_SIZE
            )
        }
    }
}