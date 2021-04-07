package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View

class CheckOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
    }

    fun submit(view : View){
        val greeting = "Welcome To Payment Page"

        val intent2 = Intent(this, Payment::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
        }
        startActivity(intent2)
    }

    fun back(view : View){
        val greeting = "Back To Main Page"

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
        }
        startActivity(intent2)
    }

}