package com.jasson.quicknote.model

import java.io.Serializable

data class Note(val id: Long=0, val title: String="", val priority: Boolean=false, val content: String="",val photoPath: String=""): Serializable