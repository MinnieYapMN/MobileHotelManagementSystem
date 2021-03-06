package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_staffmaintenance.*

class staffmaintenance : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    companion object{
        const val MAIL = "MAIL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staffmaintenance)
        val mail = intent.getStringExtra(staffmaintenance.MAIL)
        val resultMail = findViewById<TextView>(R.id.txtStaffEmail)
        resultMail.text = "" + mail


        buttonSAdd.setOnClickListener{
            var StaffId = txtStaffId.text.toString()
            var StaffName = txtStaffName.text.toString()
            var StaffGender = txtStaffGender.text.toString()
            var StaffBirth = txtStaffBirth.text.toString()
            var StaffEmail = txtStaffEmail.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Staff")
            val Staff = Staff(StaffId,StaffName,StaffGender,StaffBirth,StaffEmail)
            database.child(StaffId).setValue(Staff).addOnSuccessListener {


                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }
        }

        buttonSUpdate.setOnClickListener {

            var StaffId = txtStaffId.text.toString()
            var StaffName = txtStaffName.text.toString()
            var StaffGender = txtStaffGender.text.toString()
            var StaffBirth = txtStaffBirth.text.toString()
            var StaffEmail = txtStaffEmail.text.toString()

            updateData(StaffId,StaffName,StaffGender,StaffBirth,StaffEmail)

        }

    }

    private fun updateData(StaffId: String, StaffName: String, StaffGender : String, StaffBirth: String,StaffEmail:String) {

        database = FirebaseDatabase.getInstance().getReference("Staff")
        val Staff = mapOf<String,String>(
                "staffId" to StaffId,
                "staffName" to StaffName,
                "staffGender" to StaffGender,
                "staffBirth" to StaffBirth,
                "staffEmail" to StaffEmail
        )

        database.child(StaffId).updateChildren(Staff).addOnSuccessListener {


            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}

    fun SShow(view: View) {
        val greeting = "Show Staff Details"

        val intent = Intent(this, staffdetails::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

    fun SBack(view: View) {
        val greeting = "Main Page"

        val intent = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }

}