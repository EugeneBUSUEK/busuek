package com.example.busuek.data

import com.example.busuek.domain.Movie
import com.example.busuek.domain.MoviesRepository

object MoviesRepositoryImpl: MoviesRepository {

    private val movieList = mutableListOf<Movie>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val movie = Movie("Name $i",
                "",
                "",
                "",
                "",
                "",
                false)
            addMovie(movie)
        }
    }

    override fun addMovie(movie: Movie) {
        if (movie.id == Movie.UNDEFINED_ID) {
            movie.id = autoIncrementId
            autoIncrementId++
        }
        movieList.add(movie)
    }

    override fun deleteMovie(movie: Movie) {
        movieList.remove(movie)
    }

    override fun editMovie(movie: Movie) {
        val oldMovie = getMovie(movie.id)
        movieList.remove(movie)
        addMovie(movie)
    }

    override fun getMovie(movieId: Int): Movie {
        return movieList.find {
            it.id == movieId
        } ?: throw RuntimeException("Movie with $movieId not found")
    }

    override fun getMovieList(): List<Movie> {
        return movieList.toList()
    }
}
