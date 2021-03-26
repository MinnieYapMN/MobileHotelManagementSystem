package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun signIn(view : View){
        val greeting = "Welcome to Login Page"

        val intent2 = Intent(this, loginpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
        }
        startActivity(intent2)
        }
    }
}