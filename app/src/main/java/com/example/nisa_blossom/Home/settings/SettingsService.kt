package com.example.nisa_blossom.Home.pertemuan_9.settings

import android.content.Context
import android.content.SharedPreferences

class SettingsService(private val context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("settings_pref", Context.MODE_PRIVATE)

    /**
     * Get all settings menu data
     */
    fun getSettingsMenu(): List<SettingsModel> {
        return listOf(
            SettingsModel(1, "🔔", "Notifikasi Proyek", "Info jadwal dan rapat proyek", true),
            SettingsModel(2, "🌐", "Lokasi Proyek", "Pilih site office", false),
            SettingsModel(3, "🔒", "Data Proyek", "Dokumen tender & kontrak", false),
            SettingsModel(4, "🌙", "Mode Lapangan", "Tampilan untuk kondisi lapangan", true),
            SettingsModel(5, "❓", "Bantuan Proyek", "Panduan aplikasi manajemen proyek", false),
            SettingsModel(6, "ℹ️", "Tentang Aplikasi", "Manajemen Proyek Konstruksi v1.0", false)
        )
    }

    /**
     * Save notification setting
     */
    fun saveNotificationEnabled(isEnabled: Boolean) {
        sharedPref.edit().putBoolean("notification_enabled", isEnabled).apply()
    }

    /**
     * Get notification setting
     */
    fun isNotificationEnabled(): Boolean {
        return sharedPref.getBoolean("notification_enabled", true)
    }

    /**
     * Save dark mode setting
     */
    fun saveDarkModeEnabled(isEnabled: Boolean) {
        sharedPref.edit().putBoolean("dark_mode_enabled", isEnabled).apply()
    }

    /**
     * Get dark mode setting
     */
    fun isDarkModeEnabled(): Boolean {
        return sharedPref.getBoolean("dark_mode_enabled", false)
    }
}