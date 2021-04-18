package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_check_in.*
import kotlinx.android.synthetic.main.activity_reserve.*


class CheckIn : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        ciGet.setOnClickListener {
            val name: String = ciName.text.toString()

            if (name.isNotEmpty()){
                getData(name)
                CISubmit.setOnClickListener {
                    var RoomNo = ciRno.text.toString()
                    database = FirebaseDatabase.getInstance().getReference("RoomReserve")
                    database.child(name).child("roomNo").setValue(RoomNo)

                    val greeting = "Back To Main Page"
                    val intent2 = Intent(this, mainpage::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
                    }
                    startActivity(intent2)
                }
            }else{
                Toast.makeText(this,"Please enter the guest name.",Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun back(view : View){
        val greeting = "Back To Main Page"

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
        }
        startActivity(intent2)
    }

    fun submit(view : View){
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

                val GPhone = it.child("gphone").value
                val ERoom = it.child("eroom").value
                val DRoom = it.child("droom").value
                val NoOfAdult = it.child("noOfAdult").value
                val NoOfChild = it.child("noOfChild").value
                Toast.makeText(this,"Guest information got succefully.",Toast.LENGTH_SHORT).show()


                ciPhone.text = GPhone.toString()
                ciERoom.text = ERoom.toString()
                ciDRoom.text = DRoom.toString()
                ciNoAdult.text = NoOfAdult.toString()
                ciNoChildren.text = NoOfChild.toString()

            }else{

                Toast.makeText(this,"The guest doesn't exists.",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed to get data.",Toast.LENGTH_SHORT).show()

        }
    }

}

