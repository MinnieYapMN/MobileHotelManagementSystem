package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class loginpage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        var database = FirebaseDatabase.getInstance().reference

        btnLogin.se{
            var Username = EditText.text.toString()
            var txtPassword = EditText.text.toString().toInt()

        }
    }

    fun next(view: View) {
        var et_user_name = findViewById(R.id.txtName) as EditText
        var et_password = findViewById(R.id.txtPassword) as EditText
        var btn_submit = findViewById(R.id.btnLogin) as Button

        val user_name = et_user_name.text;
        val password = et_password.text;
        Toast.makeText(this, user_name, Toast.LENGTH_LONG).show()

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, user_name)
        }
        startActivity(intent2)
    }
    }


