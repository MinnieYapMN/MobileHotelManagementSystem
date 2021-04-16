package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }

    fun back(view: View) {
        val greeting = "Back To Main Page"

        val intent2 = Intent(this, mainpage::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent2)
    }

    fun Logout(view: View) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logout.", Toast.LENGTH_LONG).show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
