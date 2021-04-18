package com.example.mobilehotelmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_reserve.*

class reserve : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        var database = FirebaseDatabase.getInstance()
        var roomR = database.getReference("RoomReserve")


        rDone.setOnClickListener{
            var GName = rName.text.toString()
            var GPhone = rPhone.text.toString()
            var ERoom = rExecutiveRoom.text.toString().toInt()
            var DRoom = rDRoom.text.toString().toInt()
            var CheckInDate = rciDate.text.toString()
            var NoOfDay = rNoDay.text.toString().toInt()
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

            val total:Int = (ERM + DRM) * NoOfDay


            roomR.child(GName.toString()).setValue(Guest(GName,GPhone,ERoom,DRoom, CheckInDate,NoOfDay, NoOfAdult,NoOfChild,ERM,DRM, total, RoomNo))
            Toast.makeText(this, "Updated successful.", Toast.LENGTH_LONG).show()

        }
    }


}