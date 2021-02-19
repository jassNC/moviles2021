package com.jasson.example.s5


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class CustomAdapter(private val myList:ArrayList<Footage>,
                    private val movieSelectionListener: MovieSelectionListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_movie_cell, parent, false)
        return ViewHolder(view, movieSelectionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCell(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun updateFootage(updatedFootage: ArrayList<Footage>){
        myList.clear()
        myList.addAll(updatedFootage)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View,
                     private val movieSelectionListener: MovieSelectionListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
                                                                                                                    View.OnLongClickListener{

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }


        override fun onClick(v: View?) {
            movieSelectionListener.onMovieSelected(adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            movieSelectionListener.onMovieSelectedForDelete(adapterPosition)
            return true
        }


        fun bindCell(movie: Footage) {
            try {
                val titleTextView = itemView.findViewById(R.id.title) as TextView
                val genreTextView = itemView.findViewById(R.id.genre) as TextView
                val abstract = itemView.findViewById(R.id.abs) as TextView
                val poster = itemView.findViewById(R.id.poster) as ImageView
                val type = itemView.findViewById(R.id.type) as TextView

                titleTextView.text = movie.title
                genreTextView.text = movie.genre
                abstract.text = movie.abs
                type.text = movie.type
                poster.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                    itemView.resources.getIdentifier(movie.poster,"drawable","com.jasson.example.s5"))!!)
            }catch(ex: Exception){

            }

        }
    }
}