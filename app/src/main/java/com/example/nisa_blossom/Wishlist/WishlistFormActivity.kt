package com.example.nisa_blossom.Wishlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nisa_blossom.Data.AppDatabase
import com.example.nisa_blossom.Data.entity.WishlistEntity
import com.example.nisa_blossom.databinding.ActivityWishlistFormBinding
import kotlinx.coroutines.launch

class WishlistFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistFormBinding
    private lateinit var db: AppDatabase

    // 1. Tambahkan variabel wishlistId (0 artinya tambah data baru)
    private var wishlistId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWishlistFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // 2. Ambil data dari Intent di dalam onCreate()
        wishlistId = intent.getIntExtra("id", 0)

        if (wishlistId != 0) {
            binding.etFlowerName.setText(intent.getStringExtra("flowerName"))
            binding.etPrice.setText(intent.getStringExtra("price"))
            binding.etDescription.setText(intent.getStringExtra("description"))

            // Opsional: Ubah teks tombol jika sedang mengedit
            binding.btnSaveWishlist.text = "Update Wishlist"
        }

        // 3. Logika tombol simpan (Tambah atau Update)
        binding.btnSaveWishlist.setOnClickListener {

            val flowerName = binding.etFlowerName.text.toString().trim()
            val price = binding.etPrice.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()

            // Validasi input kosong
            if (flowerName.isEmpty() || price.isEmpty() || description.isEmpty()) {
                if (flowerName.isEmpty()) binding.etFlowerName.error = "Nama bunga harus diisi"
                if (price.isEmpty()) binding.etPrice.error = "Harga harus diisi"
                if (description.isEmpty()) binding.etDescription.error = "Deskripsi harus diisi"
                return@setOnClickListener
            }

            lifecycleScope.launch {

                if (wishlistId == 0) {
                    // Simpan data baru jika wishlistId bernilai 0
                    db.wishlistDao().insert(
                        WishlistEntity(
                            flowerName = flowerName,
                            price = price,
                            description = description
                        )
                    )
                } else {
                    // Update data lama berdasarkan ID yang dikirim jika wishlistId bukan 0
                    db.wishlistDao().update(
                        WishlistEntity(
                            id = wishlistId,
                            flowerName = flowerName,
                            price = price,
                            description = description
                        )
                    )
                }

                finish() // Tutup activity dan kembali ke fragment_wishlist
            }
        }
    }
}