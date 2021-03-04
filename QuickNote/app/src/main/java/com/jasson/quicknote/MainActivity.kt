package com.jasson.quicknote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.jasson.quicknote.model.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NoteSelectionListener {
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var customAdapter: CustomAdapter?=null
    companion object{
        var noteList = ArrayList<Note>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllNotes()
        addNoteBtn.setOnClickListener{
            val intent = Intent(this,WriteNoteActivity::class.java)
            intent.putExtra("index", noteList.size)
            startActivity(intent)
        }
        my_recycler_view.layoutManager = StaggeredGridLayoutManager(1,RecyclerView.VERTICAL)
        customAdapter = CustomAdapter(noteList,this)
        my_recycler_view.adapter = customAdapter
    }

    override fun onNoteSelectedForDelete(noteIndex: Int) {
        deleteNote(noteList[noteIndex])
    }

    override fun onNoteSelected(noteIndex: Int) {
        val detailedIntent = Intent(this,WriteNoteActivity::class.java)
        detailedIntent.putExtra("note", noteList[noteIndex])
        detailedIntent.putExtra("index", noteList[noteIndex].id.toInt())
        startActivity(detailedIntent)
    }



    private fun getAllNotes(){
        database.collection("notes").get().addOnCompleteListener(){
            when{
                it.isSuccessful->{
                    var noteList2 = ArrayList<Note>()
                    noteList.clear()
                    for(document in it.result!!){
                        val note = document.toObject(Note::class.java)
                        when(note.priority){
                            true->noteList.add(note)
                            false->noteList2.add(note)
                        }
                    }
                    noteList.addAll(noteList2)
                    customAdapter!!.notifyDataSetChanged()
                }
                else->{
                    Toast.makeText(this,R.string.failedRetrieval,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun deleteNote(note: Note){
        database.collection("notes").document("note#${note.id}").delete().addOnCompleteListener{
            Toast.makeText(this,R.string.deleteSuccess,Toast.LENGTH_SHORT).show()
            getAllNotes()
        }.addOnFailureListener{
            Toast.makeText(this,R.string.failedConnection,Toast.LENGTH_SHORT).show()
        }
    }
}