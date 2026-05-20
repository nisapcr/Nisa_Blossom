package com.example.nisa_blossom.Home.pertemuan_10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nisa_blossom.R

data class ProductModel(
    val name: String,
    val price: String,
    val imageUrl: String,
    val location: String
)