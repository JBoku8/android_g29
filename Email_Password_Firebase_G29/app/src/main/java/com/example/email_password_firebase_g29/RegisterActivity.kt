package com.example.email_password_firebase_g29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : AppCompatActivity() {
    private lateinit var regEmailEditText: EditText
    private lateinit var regPwdEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginPageButton: Button


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        regEmailEditText = findViewById(R.id.regEmailEditText)
        regPwdEditText = findViewById(R.id.regPwdEditText)
        registerButton = findViewById(R.id.registerButton)
        loginPageButton = findViewById(R.id.loginPageButton)

        loginPageButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        registerButton.setOnClickListener {
            val email: String = regEmailEditText.text.toString()
            val password: String = regPwdEditText.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
                Toast.makeText(this, "გთხოვ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(email)
                                .build()

                            user?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener(OnCompleteListener<Void?> { userUpdated ->
                                    if (userUpdated.isSuccessful) {
                                        navigateToMain()
                                    }
                                })

                        } else {

                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            navigateToMain()
        }
    }

    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}