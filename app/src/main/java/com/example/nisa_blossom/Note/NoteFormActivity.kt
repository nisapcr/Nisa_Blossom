package com.example.nisa_blossom.Note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nisa_blossom.Data.AppDatabase
import com.example.nisa_blossom.Data.entity.NoteEntity
import com.example.nisa_blossom.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    // UPDATE: Variabel untuk menampung ID Catatan (0 berarti data baru)
    private var noteId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // UPDATE: Ambil data dari Intent jika tombol Edit diklik dari halaman sebelumnya
        noteId = intent.getIntExtra("id", 0)

        // Jika noteId bukan 0, berarti ini mode Edit. Isi form dengan data lama.
        if (noteId != 0) {
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etContent.setText(intent.getStringExtra("content"))

            // Opsional: Mengubah text tombol jadi 'Update' agar user tidak bingung
            binding.btnSaveNote.text = "Update Note"
        }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            // Validasi sederhana agar data kosong tidak tersimpan
            if (title.isEmpty() || content.isEmpty()) {
                if (title.isEmpty()) binding.etTitle.error = "Judul tidak boleh kosong"
                if (content.isEmpty()) binding.etContent.error = "Isi tidak boleh kosong"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                // UPDATE: Cek apakah Simpan Baru atau Update Data
                if (noteId == 0) {
                    // Tambah data baru
                    db.noteDao().insert(
                        NoteEntity(
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                    )
                } else {
                    // Update data yang sudah ada (menggunakan ID yang sama)
                    db.noteDao().update(
                        NoteEntity(
                            id = noteId,
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                    )
                }

                finish() // Menutup form dan kembali ke halaman utama
            }
        }
    }
}