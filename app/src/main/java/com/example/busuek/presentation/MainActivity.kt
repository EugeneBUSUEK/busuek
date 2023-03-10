package com.example.busuek.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.busuek.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movieList.observe(this) {
            movieListAdapter.submitList(it)
        }
//        val buttonAddMovie = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
//        buttonAddMovie.setOnClickListener {
//            val intent = MovieActivity.newIntentAddMovie(this)
//            startActivity(intent)
//        }
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
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvMoviesList)
    }

    private fun setupSwipeListener(rvMoviesList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val movie = movieListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteMovie(movie)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvMoviesList)
    }

    private fun setupClickListener() {
        movieListAdapter.onMovieClickListener = {
            Log.d("MainActivity", it.toString())
            val intent = MovieActivity.newIntentEditMovie(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupLongClickListener() {
        movieListAdapter.onMovieLongClickListener = {
            viewModel.changeLikedState(it)
        }
    }
}