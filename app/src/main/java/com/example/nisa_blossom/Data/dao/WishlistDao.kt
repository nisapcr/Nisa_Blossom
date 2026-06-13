package com.example.nisa_blossom.Data.dao

import androidx.room.*
import com.example.nisa_blossom.Data.entity.WishlistEntity

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist ORDER BY id DESC")
    suspend fun getAll(): List<WishlistEntity>

    @Insert
    suspend fun insert(wishlist: WishlistEntity)

    @Update
    suspend fun update(wishlist: WishlistEntity)

    @Delete
    suspend fun delete(wishlist: WishlistEntity)
}