package com.example.busuek.domain

class DeleteMovieUseCase(private val moviesRepository: MoviesRepository) {

    fun deleteMovie(movie: Movie) {
        moviesRepository.deleteMovie(movie)
    }
}