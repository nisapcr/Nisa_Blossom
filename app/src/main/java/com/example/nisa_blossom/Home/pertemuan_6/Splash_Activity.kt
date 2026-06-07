package com.example.nisa_blossom.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.Home.onboarding.OnBoardingActivity
import com.example.nisa_blossom.R

class Splash_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(
                Intent(
                    this,
                    OnBoardingActivity::class.java
                )
            )

            finish()

        }, 2000)

    }
}