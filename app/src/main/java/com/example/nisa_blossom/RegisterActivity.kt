package com.example.nisa_blossom

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spAgama = findViewById<Spinner>(R.id.spAgama)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirm = findViewById<EditText>(R.id.etConfirm)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Dropdown
        val agamaList = arrayOf("Islam","Kristen","Hindu","Budha","Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agamaList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAgama.adapter = adapter

        // DatePicker
        etTanggal.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this,
                { _, y, m, d ->
                    etTanggal.setText("$d/${m+1}/$y")
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnRegister.setOnClickListener {

            val nama = etNama.text.toString().trim()
            val tanggal = etTanggal.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirm = etConfirm.text.toString().trim()

            val genderId = rgGender.checkedRadioButtonId
            val gender = when (genderId) {
                R.id.rbL -> "Laki-laki"
                R.id.rbP -> "Perempuan"
                else -> ""
            }

            val agama = spAgama.selectedItem.toString()

            var isValid = true

            // 🔴 VALIDASI NAMA
            if (nama.isEmpty()) {
                etNama.error = "Nama wajib diisi"
                isValid = false
            }

            // 🔴 VALIDASI TANGGAL
            if (tanggal.isEmpty()) {
                etTanggal.error = "Tanggal wajib diisi"
                isValid = false
            }

            // 🔴 VALIDASI GENDER (TANPA TOAST)
            if (genderId == -1) {
                val rb = findViewById<RadioButton>(R.id.rbL)
                rb.error = "Pilih jenis kelamin"
                isValid = false
            }

            // 🔴 VALIDASI AGAMA
            if (agama.isEmpty()) {
                val tv = spAgama.selectedView as? TextView
                tv?.error = "Pilih agama"
                isValid = false
            }

            // 🔴 VALIDASI USERNAME
            if (username.isEmpty()) {
                etUsername.error = "Username wajib diisi"
                isValid = false
            }

            // 🔴 VALIDASI PASSWORD
            if (password.isEmpty()) {
                etPassword.error = "Password wajib diisi"
                isValid = false
            }

            // 🔴 VALIDASI CONFIRM PASSWORD
            if (confirm.isEmpty()) {
                etConfirm.error = "Confirm Password wajib diisi"
                isValid = false
            }

            // 🔴 VALIDASI PASSWORD HARUS SAMA
            if (password.isNotEmpty() && confirm.isNotEmpty() && password != confirm) {
                etConfirm.error = "Password tidak sama"
                isValid = false
            }

            // 🚫 STOP kalau ada error
            if (!isValid) return@setOnClickListener

            // 🟢 SIMPAN KE SHARED PREFERENCES
            val sp = getSharedPreferences("user_pref", MODE_PRIVATE)
            val edit = sp.edit()

            edit.putString("nama", nama)
            edit.putString("tanggal", tanggal)
            edit.putString("gender", gender)
            edit.putString("agama", agama)
            edit.putString("username", username)
            edit.putString("password", password)
            edit.apply()

            // SUCCESS (boleh pakai Toast)
            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}