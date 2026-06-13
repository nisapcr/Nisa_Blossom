package com.example.nisa_blossom.Data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class WishlistEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val flowerName: String,

    val price: String,

    val description: String
)