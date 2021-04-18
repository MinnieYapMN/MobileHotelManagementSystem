package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_check_out.*

class CheckOut : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        val roomEt = findViewById<EditText>(R.id.roomET)
        val nameEt = findViewById<EditText>(R.id.nameET)
        val dateEt = findViewById<EditText>(R.id.dateET)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {
            val greeting = "Welcome To Payment Page"

            var name = nameEt.text.toString()
            var room = roomEt.text.toString()
            var date = dateEt.text.toString()

            val intent = Intent(this, Payment::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, greeting)

                intent.putExtra("name", name)
                intent.putExtra("room", room)
                intent.putExtra("date", date)
            }
            startActivity(intent)
        }
    }


    fun back(view : View){
        val greeting = "Back To Main Page"

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
        }
        startActivity(intent2)
    }

}