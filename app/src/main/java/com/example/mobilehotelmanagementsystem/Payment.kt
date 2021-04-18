package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search_services.*

class Payment : AppCompatActivity() {
    companion object{
        const val NAME = "NAME"
        const val ROOM = "ROOM"
        const val CDATE = "CDATE"
    }
    private lateinit var database: DatabaseReference
    private lateinit var pdatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val name = intent.getStringExtra(NAME)
        val room = intent.getStringExtra(ROOM)
        val date = intent.getStringExtra(CDATE)

        //text view
        val resultName = findViewById<TextView>(R.id.tvCustName)
        val resultRoom = findViewById<TextView>(R.id.tvRoom)
        val resultDate = findViewById<TextView>(R.id.tvDate)

        resultName.text = "" + name
        resultRoom.text = "" + room
        resultDate.text = "" + date

        readServices(name.toString(),room.toString())

    }

    private fun readServices(name: String,room: String) {
        pdatabase = FirebaseDatabase.getInstance().getReference("RoomReserve")
        database = FirebaseDatabase.getInstance().getReference("RoomService")
        database.child(room).get().addOnSuccessListener {
            if(it.exists()){
                val serCharge = it.child("serCharge").value
                val serCharge1:Int = serCharge.toString().toInt()
                Toast.makeText(this,"Successfully read!",Toast.LENGTH_SHORT).show()
                val resultSerCharge = findViewById<TextView>(R.id.tvSerCharge)
                resultSerCharge.text = ""+ serCharge1

                pdatabase.child(name).get().addOnSuccessListener {
                    if(it.exists()){
                        val rtotal = it.child("total").value
                        val rtotal1:Int = rtotal.toString().toInt()
                        Toast.makeText(this,"Successfully read!",Toast.LENGTH_SHORT).show()
                        val resultTotal = findViewById<TextView>(R.id.tvPayment)
                        val total: Int =  serCharge1 + rtotal1
                        resultTotal.text = total.toString()
                    }else{
                        Toast.makeText(this,"Room doesn't have any services",Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Room doesn't have any services",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }


    }

    fun back(view: View) {
        val greeting = "Back To Main Page"

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent2)
    }

    fun Logout(view: View) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logout Successful.", Toast.LENGTH_LONG).show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
