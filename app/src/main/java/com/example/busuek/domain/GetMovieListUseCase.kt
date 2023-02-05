package com.example.busuek.domain

class GetMovieListUseCase(private val moviesRepository: MoviesRepository) {

    fun getMovieList(): List<Movie> {
        return moviesRepository.getMovieList()
    }
}