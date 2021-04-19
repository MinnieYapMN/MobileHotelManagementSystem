package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_out.*

class CheckOut : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bdatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val submitBtn = findViewById<Button>(R.id.submitBtn)

        coGet.setOnClickListener {
            val room: String = roomET.text.toString()

            if (room.isNotEmpty()){
                getData(room)

                var RoomNo = roomET.text.toString()
                bdatabase = FirebaseDatabase.getInstance().getReference("Room")
            }
            else{
                Toast.makeText(this,"Please enter the room no.", Toast.LENGTH_SHORT).show()
            }
        }



        submitBtn.setOnClickListener {
            val name = nameET.text.toString()
            val room = roomET.text.toString()
            val date = dateET.text.toString()

            val intent = Intent(this, Payment::class.java)
            intent.putExtra(Payment.NAME, name)
            intent.putExtra(Payment.ROOM, room)
            intent.putExtra(Payment.CDATE, date)

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

    private fun getData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("RoomReserve")
        database.child(name).get().addOnSuccessListener {

            if (it.exists()){

                val GName = it.child("gname").value
                Toast.makeText(this,"Guest information got successful.",Toast.LENGTH_SHORT).show()
                nameET.text = GName.toString()

            }else{

                Toast.makeText(this,"The guest doesn't exists.",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed to get data.",Toast.LENGTH_SHORT).show()

        }
    }


}