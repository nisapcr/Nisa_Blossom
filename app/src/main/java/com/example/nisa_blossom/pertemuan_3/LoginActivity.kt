package com.example.nisa_blossom.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.databinding.ActivityLoginBinding
import com.example.nisa_blossom.pertemuan_4.DashboardActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()

            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
    }
}