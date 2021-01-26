package com.example.email_password_firebase_g29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        supportActionBar?.hide()
    }

    fun backToLogin(v: View){
        finish()
    }
}