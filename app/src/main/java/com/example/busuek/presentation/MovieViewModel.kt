package com.example.busuek.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.busuek.data.MoviesRepositoryImpl
import com.example.busuek.domain.AddMovieUseCase
import com.example.busuek.domain.EditMovieUseCase
import com.example.busuek.domain.GetMovieUseCase
import com.example.busuek.domain.Movie

class MovieViewModel : ViewModel() {

    private val repository = MoviesRepositoryImpl
    private val getMovieUseCase = GetMovieUseCase(repository)
    private val addMovieUseCase = AddMovieUseCase(repository)
    private val editMovieUseCase = EditMovieUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getMovie(movieId: Int) {
        val movie = getMovieUseCase.getMovie(movieId)
        _movie.value = movie
    }

    fun addMovie(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val movie = Movie(name, "", "", "", "", "", false, count)
            addMovieUseCase.addMovie(movie)
            finishWork()
        }
    }

    fun editMovie(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _movie.value?.let {
                val movie = it.copy(name = name, id = count)
                editMovieUseCase.editMovie(movie)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }

    public fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}