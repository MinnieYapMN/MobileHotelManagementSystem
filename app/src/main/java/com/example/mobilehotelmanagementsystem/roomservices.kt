package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class roomservices : AppCompatActivity() {

    lateinit var txtRoom: EditText
    lateinit var checkBox: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var checkBox3: CheckBox
    lateinit var checkBox4: CheckBox
    lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_services)

        var database = FirebaseDatabase.getInstance().reference

        txtRoom = findViewById(R.id.txtRoom)
        checkBox = findViewById(R.id.checkBox)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            saveHero()
        }
    }

    private fun saveHero() {
        val num = txtRoom.text.toString().trim()

        if (num.isEmpty()) {
            txtRoom.error = "Please enter a ROOM NO"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("heroes")
        val heroId = ref.push().key

       // val hero = Hero(heroId,num)

        //ref.child(heroId).setValue(hero).addOnCompleteListener {
       //   Toast.makeText(applicationContext,"Saved Successfully!",Toast.LENGTH_LONG).show()
       // }
    }


    fun back(view: View) {
        val greeting = "Main Page"

        val intent = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
}
