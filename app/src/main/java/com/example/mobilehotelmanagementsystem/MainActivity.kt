package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        btn_SignUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
        btn_Login.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if (et_email.text.toString().isEmpty()) {
            et_email.error = "Please enter email!"
            et_email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches()) {
            et_email.error = "Please enter valid email!"
            et_email.requestFocus()
            return
        }
        if (et_pass.text.toString().isEmpty()) {
            et_pass.error = "Please enter password!"
            et_pass.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(et_email.text.toString(), et_pass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Login failed.Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {

                startActivity(Intent(this, mainpage::class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Please verify your email address.", Toast.LENGTH_LONG)
                    .show()
            }
        }else {
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_LONG).show()
        }
    }
}






