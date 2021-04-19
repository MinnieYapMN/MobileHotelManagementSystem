package com.example.mobilehotelmanagementsystem

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_reserve.*
import kotlinx.android.synthetic.main.activity_reserve.rDone
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class reserve : AppCompatActivity() {
    private lateinit var database: DatabaseReference
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
        setContentView(R.layout.activity_reserve)

        var database = FirebaseDatabase.getInstance()
        var roomR = database.getReference("RoomReserve")

        rciDate.setOnClickListener {
            val datepicker = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                        rciDate.setText(String.format("%02d-%02d-%02d", myear, mmonth + 1, mdayOfMonth))
                    }, year, month, day
            )

            datepicker.show()
        }
        rcoDate .setOnClickListener {
            val datepicker2 = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->

                        rcoDate.setText(String.format("%02d-%02d-%02d", myear, mmonth + 1, mdayOfMonth))

                    }, year, month, day
            )

            datepicker2.show()
        }
        rNoDay.setOnClickListener {
            val start = LocalDate.parse(rciDate.text)
            val end = LocalDate.parse(rcoDate.text)
            val night = start.until(end, ChronoUnit.DAYS)

            rNoDay.text = "${night.toString()} day"
        }

        rDone.setOnClickListener{
            var GName = rName.text.toString()
            var GPhone = rPhone.text.toString()
            var ERoom = rExecutiveRoom.text.toString().toInt()
            var DRoom = rDRoom.text.toString().toInt()
            var rciDate = rciDate.text.toString()
            var rcoDate = rcoDate.text.toString()
            var rNoDay = rNoDay.text.toString().toInt()
            var NoOfAdult = rNoAdult.text.toString()
            var NoOfChild = rNoChild.text.toString()
            var ERM: Int = 0
            var DRM: Int = 0
            var RoomNo = " "


            if(ERoom>0){
                ERM = ERoom*150
            }else{
                ERoom = 0
                ERM=0
            }

            if (DRoom>0){
                DRM = DRoom*250
            }else{
                DRoom = 0
                DRM = 0
            }

            val total:Int = (ERM + DRM) * rNoDay


            roomR.child(GName.toString()).setValue(Guest(GName,GPhone,ERoom,DRoom, rciDate,rcoDate,rNoDay, NoOfAdult,NoOfChild,ERM,DRM, total, RoomNo))
            Toast.makeText(this, "Updated successful.", Toast.LENGTH_LONG).show()

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