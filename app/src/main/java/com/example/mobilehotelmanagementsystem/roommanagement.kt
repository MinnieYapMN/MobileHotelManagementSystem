package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_roommanagement.*
import kotlinx.android.synthetic.main.activity_staffmaintenance.*
import java.util.Arrays.toString


class roommanagement : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roommanagement)


        buttonRAdd.setOnClickListener{
            var RoomId = txtRoomId.text.toString()
            var RoomType = txtRoomType.text.toString()
            var RoomPrice = txtRoomPrice.text.toString()
            var RoomStatus = txtRoomStatus.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Room")
            val Room = Room(RoomId,RoomType, RoomPrice, RoomStatus)
            database.child(RoomId).setValue(Room).addOnSuccessListener {

                txtRoomId.text.clear()
                txtRoomType.text.clear()
                txtRoomPrice.text.clear()
                txtRoomStatus.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }
        }

        buttonRUpdate.setOnClickListener {

            var RoomId = txtRoomId.text.toString()
            var RoomType = txtRoomType.text.toString()
            var RoomPrice = txtRoomPrice.text.toString()
            var RoomStatus = txtRoomStatus.text.toString()

            updateData(RoomId,RoomType,RoomPrice,RoomStatus)

        }

    }

    private fun updateData(RoomId: String, RoomType: String, RoomPrice: String, RoomStatus: String) {
        database = FirebaseDatabase.getInstance().getReference("Room")
        val Room = mapOf<String,String>(
            "roomId" to RoomId,
            "roomType" to RoomType,
            "roomPrice" to RoomPrice,
            "roomStatus" to RoomStatus
        )

        database.child(RoomId).updateChildren(Room).addOnSuccessListener {

            txtRoomId.text.clear()
            txtRoomType.text.clear()
            txtRoomPrice.text.clear()
            txtRoomStatus.text.clear()

            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }
    }


    fun RShow(view: View) {
        val greeting = "Show"

        val intent = Intent(this, roomdetails::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }


    fun RBack(view: View) {
        val greeting = "Main Page"

        val intent = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
}