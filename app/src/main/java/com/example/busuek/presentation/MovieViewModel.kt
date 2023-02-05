package com.example.busuek.presentation

import androidx.lifecycle.ViewModel
import com.example.busuek.data.MoviesRepositoryImpl
import com.example.busuek.domain.AddMovieUseCase
import com.example.busuek.domain.EditMovieUseCase
import com.example.busuek.domain.GetMovieUseCase
import com.example.busuek.domain.Movie

class MovieViewModel: ViewModel() {

    private val repository = MoviesRepositoryImpl
    private val getMovieUseCase = GetMovieUseCase(repository)
    private val addMovieUseCase = AddMovieUseCase(repository)
    private val editMovieUseCase = EditMovieUseCase(repository)

    fun getMovie(movieId: Int) {
        val movie = getMovieUseCase.getMovie(movieId)
    }

    fun addMovie(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val movie = Movie(name, "", "", "", "", "", false, count)
            addMovieUseCase.addMovie(movie)
        }
    }

    fun editMovie(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val movie = Movie(name, "", "", "", "", "", false, count)
            editMovieUseCase.editMovie(movie)
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
            //TODO(): show error input name
            result = false
        }
        if (count <= 0) {
            //TODO(): show error input count
            result = false
        }
        return result
    }
}