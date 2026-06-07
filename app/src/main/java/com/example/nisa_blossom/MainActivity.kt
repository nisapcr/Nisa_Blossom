package com.example.nisa_blossom.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R
import com.example.nisa_blossom.Home.pertemuan_2.Bangun_Datar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        val hitung = Bangun_Datar()

        // Hitung Luas Segitiga
        btnSegitiga.setOnClickListener {

            val alas = etAlas.text.toString().toDoubleOrNull()
            val tinggi = etTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {

                val hasil = hitung.luasSegitiga(alas, tinggi)

                tvHasil.text = "Luas Segitiga = $hasil"

                Log.d("SEGITIGA", hasil.toString())

            } else {

                Toast.makeText(
                    this,
                    "Input tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Hitung Volume Kubus
        btnKubus.setOnClickListener {

            val sisi = etSisi.text.toString().toDoubleOrNull()

            if (sisi != null) {

                val hasil = hitung.volumeKubus(sisi)

                tvHasil.text = "Volume Kubus = $hasil"

                Log.d("KUBUS", hasil.toString())

            } else {

                Toast.makeText(
                    this,
                    "Input sisi kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Tombol Kembali
        btnBack.setOnClickListener {
            finish()
        }
    }
}