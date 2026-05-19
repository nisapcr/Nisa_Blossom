package com.example.nisa_blossom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // 🔹 AUTO LOGIN
        if (isLogin) {
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
        }

        // 🔹 NAVIGASI KE REGISTER
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // 🔹 LOGIN BUTTON
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // VALIDASI INPUT
            if (username.isEmpty()) {
                binding.etUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }

            val savedUser = sharedPref.getString("username", "")
            val savedPass = sharedPref.getString("password", "")

            // 🔹 RULE 1: username = password
            if (username == password) {

                sharedPref.edit().apply {
                    putBoolean("isLogin", true)
                    putString("username", username)
                    apply()
                }

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }

            // 🔹 RULE 2: dari SharedPreferences
            else if (username == savedUser && password == savedPass) {

                sharedPref.edit().apply {
                    putBoolean("isLogin", true)
                    apply()
                }

                Toast.makeText(this, "Login dari akun terdaftar!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }

            // 🔹 GAGAL
            else {
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}