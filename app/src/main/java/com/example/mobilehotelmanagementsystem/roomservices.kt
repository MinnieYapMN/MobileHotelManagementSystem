package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_room_services.*

class roomservices : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_services)

        var database = FirebaseDatabase.getInstance()
        var roomS = database.getReference("RoomService")

        btnSave.setOnClickListener {
                var roomno = txtRoom.text.toString()
                var housekeep = cb_hk.isChecked.toString()
                var spa = cb_ss.isChecked.toString()
                var breakfast = cb_bf.isChecked.toString()
                var laundry = cb_dcnl.isChecked.toString()
                var result = ""
                result += "Selected Items: "
                if (cb_hk.isChecked) {
                    housekeep = "50"
                    result += "Housekeeping, "
                } else {
                    housekeep = "0"
                }
                if (cb_ss.isChecked) {
                    spa = "80"
                    result += "Spa, "
                } else {
                    spa = "0"
                }
                if (cb_bf.isChecked) {
                    breakfast = "100"
                    result += "Breakfast, "
                } else {
                    breakfast = "0"
                }
                if (cb_dcnl.isChecked) {
                    laundry = "40"
                    result += "Dry Cleaning and Laundry, "
                } else {
                    laundry = "0"
                }

                val serCharge: Int =
                    housekeep.toInt() + spa.toInt() + breakfast.toInt() + laundry.toInt()
                roomS.child(roomno.toString())
                    .setValue(Services(roomno, housekeep, spa, breakfast, laundry, serCharge))
                Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()

        }

    }

        fun back(view: View) {
            val greeting = "Main Page"

            val intent = Intent(this, mainpage::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
            }
            startActivity(intent)
        }

        fun search(view: View) {
            val greeting = "Searching"

            val intent = Intent(this, search_services::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
            }
            startActivity(intent)
        }
    }


