package com.example.nisa_blossom.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R
import com.example.nisa_blossom.Home.pertemuan_3.LoginActivity
import com.example.nisa_blossom.Home.pertemuan_4.DashboardActivity

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean("isLogin", false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)
    }
}
