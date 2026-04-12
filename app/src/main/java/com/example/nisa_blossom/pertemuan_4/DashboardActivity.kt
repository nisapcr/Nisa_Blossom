package com.example.nisa_blossom.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R
import com.example.nisa_blossom.pertemuan_2.MainActivity
import com.example.nisa_blossom.pertemuan_3.LoginActivity
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnBangunRuang = findViewById<Button>(R.id.btnBangunRuang)
        val btnCustom1 = findViewById<Button>(R.id.btnCustom1)
        val btnCustom2 = findViewById<Button>(R.id.btnCustom2)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        val username = intent.getStringExtra("USERNAME")

        // ✅ TAMPILKAN WELCOME
        tvWelcome.text = "Welcome, $username 👋"

        // 🔺 Bangun Ruang
        btnBangunRuang.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // 📱 Custom 1
        btnCustom1.setOnClickListener {
            val intent = Intent(this, Custom_1Activity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // 📱 Custom 2
        btnCustom2.setOnClickListener {
            val intent = Intent(this, Custom_2Activity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // 🚪 Logout
        btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(findViewById(android.R.id.content),
                        "Logout dibatalkan",
                        Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}