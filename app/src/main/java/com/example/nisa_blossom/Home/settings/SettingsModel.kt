package com.example.nisa_blossom.Home.pertemuan_9.settings

data class SettingsModel(
    val id: Int,
    val icon: String,      // Nama drawable atau emoji
    val title: String,
    val description: String,
    val hasSwitch: Boolean = false  // Apakah ada toggle switch
)