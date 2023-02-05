package com.example.busuek.domain

class AddMovieUseCase(private val moviesRepository: MoviesRepository) {

    fun addMovie(movie: Movie) {
        moviesRepository.addMovie(movie)
    }
}