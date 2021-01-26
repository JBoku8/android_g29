package com.example.email_password_firebase_g29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var signOutButton: Button
    private lateinit var updatePasswordButton: Button
    private lateinit var displayName: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        signOutButton = findViewById(R.id.signOutButton)
        displayName = findViewById(R.id.displayName)
        updatePasswordButton = findViewById(R.id.updatePasswordButton)

        signOutButton.setOnClickListener {
            signOut()
        }

        updatePasswordButton.setOnClickListener {
            val intent = Intent(this, UpdatePasswordActivity::class.java)
            startActivity(intent)
        }

        displayName.text = auth.currentUser?.displayName
    }

    private fun signOut(){
        auth.signOut()
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}