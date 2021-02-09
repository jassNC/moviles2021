package com.jasson.example.s5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.movie_detail_activity.*

class MovieDetailActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        val movie = intent.getSerializableExtra("movie") as Footage

        posterD.setImageDrawable(
            ContextCompat.getDrawable(this,
            resources.getIdentifier(movie.poster,"drawable","com.jasson.example.s5"))!!)
        titleD.text = movie.title
        genreD.text = movie.genre
        absD.text = movie.abstract
        typeD.text = movie.type
    }
}