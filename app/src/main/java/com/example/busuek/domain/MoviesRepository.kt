package com.example.busuek.domain

import androidx.lifecycle.LiveData

interface MoviesRepository {

    fun addMovie(movie: Movie)

    fun deleteMovie(movie: Movie)

    fun editMovie(movie: Movie)

    fun getMovie(movieId: Int): Movie

    fun getMovieList(): LiveData<List<Movie>>
}