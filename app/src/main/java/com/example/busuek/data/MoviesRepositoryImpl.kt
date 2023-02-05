package com.example.busuek.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.busuek.domain.Movie
import com.example.busuek.domain.MoviesRepository
import kotlin.random.Random

object MoviesRepositoryImpl: MoviesRepository {

    private val movieListLD = MutableLiveData<List<Movie>>()

    private val movieList = sortedSetOf<Movie>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrementId = 0

    init {
        for (i in 0 until 100) {
            val movie = Movie("Movie $i",
                "",
                "",
                "",
                "",
                "",
                Random.nextBoolean()
            )
            addMovie(movie)
        }
    }

    override fun addMovie(movie: Movie) {
        if (movie.id == Movie.UNDEFINED_ID) {
            movie.id = autoIncrementId
            autoIncrementId++
        }
        movieList.add(movie)
        updateList()
    }

    override fun deleteMovie(movie: Movie) {
        movieList.remove(movie)
        updateList()
    }

    override fun editMovie(movie: Movie) {
        val oldMovie = getMovie(movie.id)
        movieList.remove(oldMovie)
        addMovie(movie)
    }

    override fun getMovie(movieId: Int): Movie {
        return movieList.find {
            it.id == movieId
        } ?: throw RuntimeException("Movie with $movieId not found")
    }

    override fun getMovieList(): LiveData<List<Movie>> {
        return movieListLD
    }

    private fun updateList() {
        movieListLD.value = movieList.toList()
    }
}
