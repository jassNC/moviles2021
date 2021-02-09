package com.jasson.example.s4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        dateBtn.setOnClickListener {

            val dateDialog = DatePickerDialog(
                this,
                dateListener,
                year,
                month,
                day)
            dateDialog.show()

        }

        sendBtn.setOnClickListener{
            results.text="Data\n Email: ${editTextTextEmailAddress.text}\n Password: ${editTextTextPassword.text}" +
                    "\nPhone: ${editTextPhone.text}\n Date: ${dateText.text}\n Notifications: ${switch1.isChecked}"
            editTextTextEmailAddress.text.clear()
            editTextTextPassword.text.clear()
            editTextPhone.text.clear()
            dateText.text = ""
        }


    }
    private val dateListener = DatePickerDialog.OnDateSetListener {
            _, year, monthOfYear, dayOfMonth ->

        val realMonth = monthOfYear+1
        dateText.text="$dayOfMonth/ $realMonth/ $year"
    }
}