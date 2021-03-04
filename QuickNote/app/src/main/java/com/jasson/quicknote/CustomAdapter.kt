package com.jasson.quicknote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jasson.quicknote.model.Note
import java.lang.Exception


class CustomAdapter(private val myList:ArrayList<Note>, private val noteSelectionListener: NoteSelectionListener):RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_note_cell,parent,false)
        return ViewHolder(view, noteSelectionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCell(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun updateNotes(updatedNotes: ArrayList<Note>){
        myList.clear()
        myList.addAll(updatedNotes)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View, private val noteSelectionListener: NoteSelectionListener):RecyclerView.ViewHolder(itemView),
        View.OnClickListener,View.OnLongClickListener{

            init{
                itemView.setOnClickListener(this)
                itemView.setOnLongClickListener(this)
            }

        override fun onClick(v: View?) {
            noteSelectionListener.onNoteSelected(adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            noteSelectionListener.onNoteSelectedForDelete(adapterPosition)
            return true
        }

        fun bindCell(note: Note){
            try{
                val titleTextV = itemView.findViewById(R.id.titleField) as TextView
                val priorityImage = itemView.findViewById(R.id.has_priority) as ImageView
                val contentT = itemView.findViewById(R.id.contentTV) as TextView
                titleTextV.text = note.title
                contentT.text = note.content
                when(note.priority){
                    true->priorityImage.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.high_priority))
                    false->priorityImage.setImageDrawable(ContextCompat.getDrawable(itemView.context,R.drawable.low_priority))
                }
            }catch (ex: Exception){}
        }
        }
}