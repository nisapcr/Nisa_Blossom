package com.example.nisa_blossom.Home.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Adapter ViewPager2 kamu
        val adapter = OnBoardingAdapter(this)
        binding.viewPager.adapter = adapter

        // IMPLEMENTASI MODUL: Hubungkan Dotsindicator dengan ViewPager2 onboarding kamu
        binding.dotIndicator.attachTo(binding.viewPager)
    }
}