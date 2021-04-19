package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_roomdetails.*
import kotlinx.android.synthetic.main.activity_showr.*
import java.lang.StringBuilder

class showr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showr)


        var database = FirebaseDatabase.getInstance().getReference("Room")
        var getdata = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children) {
                    var RoomPrice = i.child("roomPrice").getValue()
                    var RoomStatus = i.child("roomStatus").getValue()
                    var RoomType = i.child("roomType").getValue()
                    sb.append("${i.key}\n $RoomPrice\n $RoomStatus\n $RoomType\n\n ")
                }
                textViewR.setText(sb)
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }

    fun sd(view: View) {
        val greeting = "Room Maintenance"

        val intent = Intent(this, roommanagement::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

}