package com.jasson.example.s5

interface MovieSelectionListener {
    fun onMovieSelected(movieIndex: Int)
    fun onMovieSelectedForDelete(movieIndex: Int)
}