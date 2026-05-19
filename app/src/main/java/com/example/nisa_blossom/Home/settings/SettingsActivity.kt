package com.example.nisa_blossom.Home.pertemuan_9.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisa_blossom.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settingsService: SettingsService
    private lateinit var settingsAdapter: SettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init Service
        settingsService = SettingsService(this)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val settingsList = settingsService.getSettingsMenu()

        settingsAdapter = SettingsAdapter(
            settingsList = settingsList,
            onItemClick = { setting ->
                // Handle item click berdasarkan ID
                when (setting.id) {
                    2 -> Toast.makeText(this, "Fitur Bahasa akan segera hadir", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(this, "Buka halaman Privasi", Toast.LENGTH_SHORT).show()
                    5 -> Toast.makeText(this, "Buka Pusat Bantuan", Toast.LENGTH_SHORT).show()
                    6 -> showAboutDialog()
                    else -> Toast.makeText(this, "Kamu memilih: ${setting.title}", Toast.LENGTH_SHORT).show()
                }
            },
            onSwitchChange = { setting, isChecked ->
                when (setting.id) {
                    1 -> {
                        settingsService.saveNotificationEnabled(isChecked)
                        val message = if (isChecked) "Notifikasi diaktifkan" else "Notifikasi dinonaktifkan"
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                    4 -> {
                        settingsService.saveDarkModeEnabled(isChecked)
                        val message = if (isChecked) "Mode gelap diaktifkan" else "Mode gelap dinonaktifkan"
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        // Apply dark mode logic here
                    }
                }
            }
        )

        binding.rvSettings.apply {
            layoutManager = LinearLayoutManager(this@SettingsActivity)
            adapter = settingsAdapter
        }
    }

    private fun showAboutDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Tentang Bina Desa")
            .setMessage("""
                Bina Desa v1.0.0
                
                Platform Digital Pemberdayaan Desa
                
                Developer: Siti Harnisa Nurhabiby
                Politeknik Caltex Riau
                
                © 2026 Bina Desa
            """.trimIndent())
            .setPositiveButton("OK", null)
            .show()
    }
}