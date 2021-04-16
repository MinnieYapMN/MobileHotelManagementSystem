package com.example.mobilehotelmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_searchdata.*

class searchdata : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var referance: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchdata)

        database = FirebaseDatabase.getInstance()
        referance = database.getReference("Staff")
        getData()
    }
    private fun getData(){
        referance.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                var list = ArrayList<Staff>()
                for (data in p0.children){
                    val staff =data.getValue(Staff::class.java)
                    list.add(staff as Staff)
                }
                if(list.size>0){
                    val adapter = DataAdapter(list)
                    recyclerview.adapter = adapter
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("cancel",p0.toString() )
            }


        })

    }

}