package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View

class mainpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
    }

    fun roomReserve(view: View){
        val greeting = "Room Reservation"

        val intent = Intent(this, roomservices::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

    fun roomServices(view: View) {
        val greeting = "Room Services"

        val intent = Intent(this, roomservices::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

    fun roomMaintian(view: View) {
        val greeting = "Room Maintenance"

        val intent = Intent(this, roommanagement::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

    fun staffMaintain(view: View) {
        val greeting = "Staff Maintenance"

        val intent = Intent(this, staffmaintenance::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

}
