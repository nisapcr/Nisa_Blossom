package com.example.nisa_blossom.pertemuan_2

import com.example.nisa_blossom.R
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val hitung = Bangun_Datar()

        // 🔺 SEGITIGA
        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull()
            val tinggi = etTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val hasil = hitung.luasSegitiga(alas, tinggi)
                tvHasil.text = "Luas Segitiga = $hasil"

                Log.d("SEGITIGA", hasil.toString())
            } else {
                Toast.makeText(this, "Input tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        // 🧊 KUBUS
        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull()

            if (sisi != null) {
                val hasil = hitung.volumeKubus(sisi)
                tvHasil.text = "Volume Kubus = $hasil"

                Log.d("KUBUS", hasil.toString())
            } else {
                Toast.makeText(this, "Input sisi kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}