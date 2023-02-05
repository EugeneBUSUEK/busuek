package com.example.busuek.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.busuek.data.MoviesRepositoryImpl
import com.example.busuek.domain.DeleteMovieUseCase
import com.example.busuek.domain.EditMovieUseCase
import com.example.busuek.domain.GetMovieListUseCase
import com.example.busuek.domain.Movie

class MainViewModel: ViewModel() {

    private val repository = MoviesRepositoryImpl

    private val getMovieListUseCase = GetMovieListUseCase(repository)
    private val deleteMovieUseCase = DeleteMovieUseCase(repository)
    private val editMovieUseCase = EditMovieUseCase(repository)

    val movieList = MutableLiveData<List<Movie>>()

    fun getMovieList() {
        val list = getMovieListUseCase.getMovieList()
        movieList.value = list
    }

    fun deleteMovie(movie: Movie) {
        deleteMovieUseCase.deleteMovie(movie)
        getMovieList()
    }

    fun changeLikedState(movie: Movie) {
        val newMovie = movie.copy(liked = !movie.liked)
        editMovieUseCase.editMovie(newMovie)
        getMovieList()
    }
}