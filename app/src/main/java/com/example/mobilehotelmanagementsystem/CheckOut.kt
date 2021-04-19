package com.example.mobilehotelmanagementsystem

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_in.*
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_reserve.*
import java.text.SimpleDateFormat
import java.util.*

class CheckOut : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bdatabase: DatabaseReference
    var formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)


    @RequiresApi(Build.VERSION_CODES.O)
    val getDate = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    val year = getDate.get(Calendar.YEAR)

    @RequiresApi(Build.VERSION_CODES.N)
    val month = getDate.get(Calendar.MONTH)

    @RequiresApi(Build.VERSION_CODES.N)
    val day = getDate.get(Calendar.DAY_OF_MONTH)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val submitBtn = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {
            val name = nameET.text.toString()
            val room = roomET.text.toString()
            val date = dateET.text.toString()

            val intent = Intent(this, Payment::class.java)
            intent.putExtra(Payment.NAME, name)
            intent.putExtra(Payment.ROOM, room)
            intent.putExtra(Payment.CDATE, date)

            startActivity(intent)

            dateET .setOnClickListener {
                val datepicker2 = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->

                        dateET.setText(String.format("%02d-%02d-%02d", myear, mmonth + 1, mdayOfMonth))

                    }, year, month, day
                )

                datepicker2.show()
            }

            if (name.isNotEmpty()){
                getData(name)

                var RoomNo = roomET.text.toString()
                var status = "Available"
                database = FirebaseDatabase.getInstance().getReference("RoomReserve")
                bdatabase = FirebaseDatabase.getInstance().getReference("Room")
                database.child(name).child("roomNo").setValue(RoomNo)
                bdatabase.child(RoomNo).child("roomStatus").setValue(status)
                val greeting = "Back To Main Page"
                val intent2 = Intent(this, mainpage::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE,greeting)
                }
                startActivity(intent2)
            }
            else{
                Toast.makeText(this,"Please enter the guest name.", Toast.LENGTH_SHORT).show()
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