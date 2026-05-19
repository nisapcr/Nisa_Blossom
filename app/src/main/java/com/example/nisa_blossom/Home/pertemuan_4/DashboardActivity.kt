package com.example.nisa_blossom.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R
import com.example.nisa_blossom.pertemuan_2.MainActivity
import com.example.nisa_blossom.Home.pertemuan_3.LoginActivity
import com.example.nisa_blossom.Home.pertemuan_6.WebView_Activity
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnBangunRuang = findViewById<Button>(R.id.btnBangunRuang)
        val btnCustom1 = findViewById<Button>(R.id.btnCustom1)
        val btnCustom2 = findViewById<Button>(R.id.btnCustom2)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        val username = intent.getStringExtra("USERNAME") ?: "User"

        tvWelcome.text = "Welcome, $username 👋"

        btnBangunRuang.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }



        btnCustom1.setOnClickListener {
            startActivity(Intent(this, Custom_1Activity::class.java))
        }

        btnCustom2.setOnClickListener {
            startActivity(Intent(this, Custom_2Activity::class.java))
        }

        // 🌐 WEB BINA DESA
        btnWeb.setOnClickListener {
            startActivity(Intent(this, WebView_Activity::class.java))
        }

        // 🚪 LOGOUT (hapus session)
        btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->

                    val sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()

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