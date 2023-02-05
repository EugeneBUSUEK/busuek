package com.example.busuek.domain

class GetMovieUseCase(private val moviesRepository: MoviesRepository) {

    fun getMovie(movieId: Int): Movie {
        return moviesRepository.getMovie(movieId)
    }
}