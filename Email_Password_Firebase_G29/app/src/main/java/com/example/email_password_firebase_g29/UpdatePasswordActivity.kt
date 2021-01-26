package com.example.email_password_firebase_g29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class UpdatePasswordActivity : AppCompatActivity() {
    // private lateinit var backToMain: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
    }

    fun backToMain(v: View){
        finish()
    }
}