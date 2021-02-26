package com.jasson.example.s5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detail_activity.*

class MovieDetailActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        val movie = intent.getSerializableExtra("movie") as Footage

        loadRemoteImage(movie.posterUrl,posterD,movie.poster)

        titleD.text = movie.title
        genreD.text = movie.genre
        absD.text = movie.abs
        typeD.text = movie.type

        urlBtn.setOnClickListener(){
            openURL(movie.url)
        }
    }

    private fun loadRemoteImage(url: String, imageView: ImageView, poster: String){
        imageView.run{
            Picasso.get().load(url).fetch()
            Picasso.get().load(url)
                .resize(500,700)
                .error(this.resources.getIdentifier(poster,"drawable","com.jasson.example.s5"))
                .into(this)
        }
    }

    fun openURL(url: String){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
    }
}