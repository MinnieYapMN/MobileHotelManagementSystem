package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search_services.*


class search_services : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_services)

        btnSearch.setOnClickListener {
            val room : String = textRoom.text.toString()
            if(room.isNotEmpty()){
                readServices(room)
            }else{
                Toast.makeText(this,"Please enter the Room No to search",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readServices(room: String) {
        database = FirebaseDatabase.getInstance().getReference("RoomService")
        database.child(room).get().addOnSuccessListener {
            if(it.exists()){
                    val housekeep = it.child("housekeep").value
                    val housekeep1:Int = housekeep.toString().toInt()
                    val spa = it.child("spa").value
                    val spa1:Int = spa.toString().toInt()
                    val breakfast = it.child("breakfast").value
                    val breakfast1:Int = breakfast.toString().toInt()
                    val laundry = it.child("laundry").value
                    val laundry1:Int = laundry.toString().toInt()
                    Toast.makeText(this,"Successfully read!",Toast.LENGTH_SHORT).show()
                    textRoom.text.clear()
                    if(housekeep1 > 0){
                        tvhk.text = "Yes"
                    }else{
                        tvhk.text = "No"
                    }
                if(spa1 > 0){
                    tvss.text = "Yes"
                }else{
                    tvss.text = "No"
                }
                if(breakfast1 > 0){
                    tvbf.text = "Yes"
                }else{
                    tvbf.text = "No"
                }
                if(laundry1 > 0){
                    tvdcnl.text = "Yes"
                }else{
                    tvdcnl.text = "No"
                }

            }else{
                Toast.makeText(this,"Room No doesn't have any services",Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }

    }

    fun back(view: View) {
        val greeting = "Main Page"

        val intent = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
}