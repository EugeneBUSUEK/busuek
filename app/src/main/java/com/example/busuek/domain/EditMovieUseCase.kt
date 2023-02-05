package com.example.busuek.domain

class EditMovieUseCase(private val moviesRepository: MoviesRepository) {

    fun editMovie(movie: Movie) {
        moviesRepository.editMovie(movie)
    }
}