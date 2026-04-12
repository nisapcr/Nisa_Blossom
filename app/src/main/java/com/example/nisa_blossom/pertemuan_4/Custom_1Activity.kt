package com.example.nisa_blossom.pertemuan_4

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R

class Custom_1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val etNama = findViewById<EditText>(R.id.etNama)
        val btnTampil = findViewById<Button>(R.id.btnTampil)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        tvWelcome.text = "Welcome, $username 👋"

        // ✅ BUTTON TAMPIL
        btnTampil.setOnClickListener {
            val nama = etNama.text.toString()

            if (nama.isNotEmpty()) {
                tvHasil.text = "Halo, $nama! 😊"
            } else {
                Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
//aliya jelek
        // 🔙 BACK
        btnBack.setOnClickListener {
            finish()
        }
    }
}