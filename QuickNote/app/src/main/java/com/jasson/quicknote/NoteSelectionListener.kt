package com.jasson.quicknote

interface NoteSelectionListener {
    fun onNoteSelected(noteIndex: Int)
    fun onNoteSelectedForDelete(noteIndex: Int)
}