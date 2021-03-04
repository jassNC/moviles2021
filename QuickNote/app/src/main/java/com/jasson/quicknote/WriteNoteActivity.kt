package com.jasson.quicknote

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.jasson.quicknote.model.Note
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_write_note.*
import kotlinx.android.synthetic.main.custom_note_cell.*
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class WriteNoteActivity : AppCompatActivity() {
    private var index: Long?=null
    private var edit = false
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val requestGalleryId = 1
    lateinit var photoImageView: ImageView
    private val requestCameraId = 2
    var photoFile: File? = null
    lateinit var currentPhotoPath: String
    private var photoPath: String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)
        photoImageView = findViewById(R.id.imageAttached)

        addImageBtn.setOnClickListener{
            takePhotoIntent()
        }

        val num = intent.getSerializableExtra("index") as Int
        try{
            val myNote = intent.getSerializableExtra("note") as Note
            edit = true
            NoteBody.setText(myNote.content)
            titleTextEdit.setText(myNote.title)
            if(myNote.photoPath!=""){
                val photoTakenBitmap = BitmapFactory.decodeFile(myNote.photoPath)
                photoPath = myNote.photoPath
                photoImageView.setImageBitmap(rotateImage(photoTakenBitmap,90))
            }
            if(myNote.priority){
                priorityCheckBox.isChecked = true
            }
        }catch (ex: Exception){}
        saveBtn.setOnClickListener{
            when(NoteBody.text.toString()!=""&&titleTextEdit.text.toString()!=""){
                true->saveChanges(num)
                false->Toast.makeText(this,R.string.emptySpace,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveChanges(num: Int){
        val title = titleTextEdit.text.toString()
        val priority = priorityCheckBox.isChecked
        val body = NoteBody.text.toString()
        val path = photoPath
        when(edit) {
            true -> {
                index = (num).toLong()
                updateNote(Note(index!!, title, priority, body,path))
            }
            false ->{
                index = (1+num).toLong()
                insertNote(Note(index!!, title, priority, body,path))
            }
        }

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun insertNote(note: Note){
        database.collection("notes").document("note#${note.id}").set(note)
            .addOnSuccessListener {
                Toast.makeText(this,R.string.noteAdded,Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,R.string.failedConnection,Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateNote(note: Note){
        database.collection("notes").document("note#${note.id}").update(mapOf(
            "content" to note.content,
            "priority" to note.priority,
            "title" to note.title,
            "photoPath" to note.photoPath
        )).addOnSuccessListener {
            Toast.makeText(this,R.string.modifiedSuccess,Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,R.string.failedConnection,Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseImageFromGallery(){
        Intent(Intent.ACTION_PICK).also {
            it.type="image/*"
            startActivityForResult(it,requestGalleryId)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==requestGalleryId&&resultCode== Activity.RESULT_OK){
            photoImageView.setImageURI(data?.data)
        }else if(requestCode==requestCameraId&&resultCode==Activity.RESULT_OK){
            photoPath=photoFile?.absolutePath.toString()
            val photoTakenBitmap = BitmapFactory.decodeFile(photoPath)
            photoImageView.setImageBitmap(rotateImage(photoTakenBitmap,90))
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun takePhotoIntent(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
            takePictureIntent->
            photoFile=try{
                createImageFile()
            }catch (ex: Exception){
                null
            }
            photoFile?.also{
                val photoURI: Uri = FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".provider",it)

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                startActivityForResult(takePictureIntent,requestCameraId)
            }

        }
    }

    @Throws(IOException::class)
    private fun createImageFile():File{
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_",".jpg",storageDir).apply {
            currentPhotoPath=absolutePath
        }
    }

    private fun rotateImage(img: Bitmap, degree: Int): Bitmap{
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImage = Bitmap.createBitmap(img,0,0,img.width,img.height,matrix,true)
        img.recycle()
        return rotatedImage
    }

}