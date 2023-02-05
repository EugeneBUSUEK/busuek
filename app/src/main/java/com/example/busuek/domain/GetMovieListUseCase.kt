package com.example.busuek.domain

import androidx.lifecycle.LiveData

class GetMovieListUseCase(private val moviesRepository: MoviesRepository) {

    fun getMovieList(): LiveData<List<Movie>> {
        return moviesRepository.getMovieList()
    }
}