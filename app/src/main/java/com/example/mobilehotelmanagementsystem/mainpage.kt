package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mainpage.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class mainpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)

        btn_Logout.setOnClickListener {
            Logout()
        }
    }

    fun roomReserve(view: View){
        val greeting = "Room Reservation"

        val intent = Intent(this,reserve::class.java).apply {
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

    fun checkIn(view: View) {
        val greeting = "Check In Page"

        val intent = Intent(this, CheckIn::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
    fun checkOut(view: View) {
        val greeting = "Check Out Page"

        val intent = Intent(this, CheckOut::class.java).apply {
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

    fun Logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logout.", Toast.LENGTH_LONG).show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
