package com.example.nisa_blossom.pertemuan_4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R

class Custom_2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        tvWelcome.text = "Welcome, $username 👋"

        btnBack.setOnClickListener {
            finish()
        }
    }
}