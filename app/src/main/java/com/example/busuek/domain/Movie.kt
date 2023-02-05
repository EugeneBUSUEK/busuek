package com.example.busuek.domain

data class Movie(
    val id: Int,
    val name: String,
    val posterUrlSmall: String,
    val year: String,
    val description: String,
    val genre: String,
    val country: String,
    val liked: Boolean
)
