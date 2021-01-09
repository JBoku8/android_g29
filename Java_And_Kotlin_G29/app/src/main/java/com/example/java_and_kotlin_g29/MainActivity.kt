package com.example.java_and_kotlin_g29

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myUser = User("John", "Doe", 23)
        val textView = findViewById<TextView>(R.id.textView)
        // val message = "${myUser.firstName} ${myUser.lastName}, ${myUser.age}"
        textView.text = myUser.getInfo()

        val rand = Random()
        val value: Int = rand.nextInt(50)

        val randomText = findViewById<TextView>(R.id.randomText);

        randomText.text = value.toString()
    }
}