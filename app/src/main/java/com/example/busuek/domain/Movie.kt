package com.example.busuek.domain

data class Movie(
    val name: String,
    val posterUrlSmall: String,
    val year: String,
    val description: String,
    val genre: String,
    val country: String,
    val liked: Boolean,
    var id: Int = -UNDEFIND_ID
) {
    companion object {
        const val UNDEFIND_ID = -1
    }
}
