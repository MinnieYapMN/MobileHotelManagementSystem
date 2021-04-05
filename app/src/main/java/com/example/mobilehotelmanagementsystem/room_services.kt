package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View

class room_services : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_services)
    }
fun back(view:View){
    val greeting = "Main Page"

    val intent = Intent(this, mainpage::class.java).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
    }
    startActivity(intent)
}
}