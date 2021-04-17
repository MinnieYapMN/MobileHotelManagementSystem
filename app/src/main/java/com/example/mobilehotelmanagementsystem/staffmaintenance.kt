package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_staffmaintenance.*

class staffmaintenance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staffmaintenance)

        var database = FirebaseDatabase.getInstance()
        var staff = database.getReference("Staff")

        buttonSAdd.setOnClickListener{
            var StaffId = txtStaffId.text.toString()
            var StaffName = txtStaffName.text.toString()
            var StaffGender = txtStaffGender.text.toString()
            var StaffBirth = txtStaffBirth.text.toString()

            staff.child(StaffId.toString()).setValue(Staff(StaffId, StaffName, StaffGender, StaffBirth))
        }

        buttonSupdate.setOnClickListener{
            startActivity(Intent(applicationContext,searchdata::class.java))
        }


    }
}