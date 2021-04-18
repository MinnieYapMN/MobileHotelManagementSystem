package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_roomdetails.*
import kotlinx.android.synthetic.main.activity_staffdetails.*



class staffdetails : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staffdetails)

        sreaddataBtn.setOnClickListener {
            val staffId : String = etstaff.text.toString()
            if  (staffId.isNotEmpty()){

                readData(staffId)

            }else{

                Toast.makeText(this,"PLease enter the Staff Id", Toast.LENGTH_SHORT).show()

            }

        }

        sdeletedataBtn.setOnClickListener {
            var staffId : String = etstaff.text.toString()
            if  (staffId.isNotEmpty()){

                deleteData(staffId)

            }else{

                Toast.makeText(this,"PLease enter the Staff Id", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun deleteData(staffId: String) {
        database = FirebaseDatabase.getInstance().getReference("Staff")
        database.child(staffId).removeValue().addOnSuccessListener {

            etstaff.text.clear()
            tvName.setText("")
            tvGender.setText("")
            tvBirth.setText("")
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }

    private fun readData(staffId: String) {

        database = FirebaseDatabase.getInstance().getReference("Staff")
        database.child(staffId).get().addOnSuccessListener {

            if (it.exists()){

                val StaffName = it.child("staffName").value
                val StaffGender = it.child("staffGender").value
                val StaffBirth = it.child("staffBirth").value
                Toast.makeText(this,"Successfully Read",Toast.LENGTH_SHORT).show()

                tvName.text = StaffName.toString()
                tvGender.text = StaffGender.toString()
                tvBirth.text = StaffBirth.toString()

            }else{

                Toast.makeText(this,"Staff Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }

    }
    fun SDBack(view: View) {
        val greeting = "Staff Maintenance"

        val intent = Intent(this, staffmaintenance::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }


}