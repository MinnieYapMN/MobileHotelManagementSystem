package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mainpage.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        btn_SignUp.setOnClickListener {
            signUpUser()
        }
    }
    fun signUpUser() {
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
        auth.createUserWithEmailAndPassword(et_email.text.toString(), et_pass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user=auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                        }
                    // Sign in success, update UI with the signed-in user's information

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun exit(view: View) {
        val greeting = "Login Page"

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, greeting)
        }
        startActivity(intent)
    }
}