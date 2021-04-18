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
            val rooms : String = textRoom.text.toString()
            if(rooms.isNotEmpty()){
                readServices(rooms)
            }else{
                Toast.makeText(this,"Please enter the Room No to search",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readServices(rooms: String) {
        database = FirebaseDatabase.getInstance().getReference("Services")
        database.child(rooms).get().addOnSuccessListener {
            if(it.exists()){
                    val housekeep = it.child("housekeep").value
                    val spa = it.child("spa").value
                    val breakfast = it.child("breakfast").value
                    val laundry = it.child("laundry").value
                    val serCharge = it.child("serCharge").value
                    Toast.makeText(this,"Successfully read!",Toast.LENGTH_SHORT).show()
                    textRoom.text.clear()
                    tvall.text = housekeep.toString()
                    tvall.text = spa.toString()
                    tvall.text = breakfast.toString()
                    tvall.text = laundry.toString()
                    tvall.text = serCharge.toString()


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