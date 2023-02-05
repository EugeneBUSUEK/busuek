package com.example.busuek.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.busuek.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieListAdapter

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movieList.observe(this) {
            adapter.moviesList = it
        }
    }

    private fun setupRecyclerView() {
        val rvMoviesList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = MovieListAdapter()
        rvMoviesList.adapter = adapter
    }
}