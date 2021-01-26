package com.example.email_password_firebase_g29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var pwdEditText: EditText
    private lateinit var goBackButton: Button
    private lateinit var signInButton: Button
    private lateinit var forgotPwdTextView: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        emailEditText = findViewById(R.id.loginEmailEditText)
        pwdEditText = findViewById(R.id.loginPwdEditText)
        goBackButton = findViewById(R.id.goBackButton)
        signInButton = findViewById(R.id.signInButton)
        forgotPwdTextView = findViewById(R.id.forgotPwdTextView)


        auth = FirebaseAuth.getInstance()

        goBackButton.setOnClickListener {
            finish()
        }

        forgotPwdTextView.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val email: String = emailEditText.text.toString()
            val password: String = pwdEditText.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
                Toast.makeText(this, "გთხოვ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }

    }
}