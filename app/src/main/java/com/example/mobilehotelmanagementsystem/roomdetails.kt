package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_roomdetails.*
import kotlinx.android.synthetic.main.activity_staffdetails.*


class roomdetails : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roomdetails)

        rreaddataBtn.setOnClickListener {
            val roomId : String = etroom.text.toString()
            if  (roomId.isNotEmpty()){

                readData(roomId)

            }else{

                Toast.makeText(this,"PLease enter the Room No", Toast.LENGTH_SHORT).show()

            }

        }

        rdeletedataBtn.setOnClickListener {
            var roomId : String = etroom.text.toString()
            if  (roomId.isNotEmpty()){

                deleteData(roomId)

            }else{

                Toast.makeText(this,"PLease enter the Room No", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun deleteData(roomId: String) {
        database = FirebaseDatabase.getInstance().getReference("Room")
        database.child(roomId).removeValue().addOnSuccessListener {

            etroom.text.clear()
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }


    private fun readData(roomId: String) {

        database = FirebaseDatabase.getInstance().getReference("Room")
        database.child(roomId).get().addOnSuccessListener {

            if (it.exists()){

                val RoomPrice = it.child("roomPrice").value
                val RoomStatus = it.child("roomStatus").value
                val RoomType = it.child("roomType").value
                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                etroom.text.clear()
                tvRPrice.text = RoomPrice.toString()
                tvRStatus.text = RoomStatus.toString()
                tvRType.text = RoomType.toString()

            }else{

                Toast.makeText(this,"Room Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }


    }

    fun RDBack(view: View) {
        val greeting = "Room Maintenance"

        val intent = Intent(this, roommanagement::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
}