package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class loginpage : AppCompatActivity(){

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    lateinit var btn_Login: Button
    lateinit var et_email: EditText
    lateinit var et_pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
        et_email = findViewById(R.id.et_email)
        et_pass = findViewById(R.id.et_pass)


        mAuth =FirebaseAuth.getInstance()

        btn_Login.setOnClickListener{
            loginUser()
        }

        }

  private fun loginUser() {
       val email: String = et_email.text.toString()
        val password: String = et_pass.text.toString()

        if(email == "")
        {
            Toast.makeText(this@loginpage,"Please enter email",Toast.LENGTH_LONG).show()
        } else if (password == ""){
            Toast.makeText(this@loginpage,"Please enter password",Toast.LENGTH_LONG).show()
        }else{
            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task->
                    if(task.isSuccessful)
                    {
                        val intent2 = Intent(this, mainpage::class.java).apply {
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        Toast.makeText(this@loginpage,"Error Message:"+ task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    /*fun next(view: View) {
        var et_email = findViewById(R.id.et_email) as EditText
        val email = et_email.text;
        Toast.makeText(this, email, Toast.LENGTH_LONG).show()
        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, email)
        }
        startActivity(intent2)
        finish()
    }*/

    }



