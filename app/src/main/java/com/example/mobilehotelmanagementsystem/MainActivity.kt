package com.example.mobilehotelmanagementsystem

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        btn_forgotPass.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val email = view.findViewById<EditText>(R.id.et_email)
            builder.setView(view)
            builder.setPositiveButton("Reset",DialogInterface.OnClickListener { _, _ ->
            forgotPassword(email)

            })
            builder.setNegativeButton("Close",DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }
    }

    private fun forgotPassword(email:EditText) {
        if (email.text.toString().isEmpty()) {
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            return
        }
        auth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email sent.", Toast.LENGTH_LONG).show()
                        //startActivity(Intent(this,resetpass::class.java))
                       // finish()
                    }

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
                Toast.makeText(baseContext, "Please verify your email address.", Toast.LENGTH_SHORT)
                    .show()
            }
        }else {
            Toast.makeText(baseContext, "Login Page.", Toast.LENGTH_SHORT).show()
        }
    }
}






