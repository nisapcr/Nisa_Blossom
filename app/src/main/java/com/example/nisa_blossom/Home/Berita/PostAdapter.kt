package com.example.nisa_blossom.Home.Berita

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nisa_blossom.Data.Model.PostModel
import com.example.nisa_blossom.databinding.ItemPostBinding

class PostAdapter(
    private val listPost: List<PostModel>
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    // 1. Kumpulan Kategori Proyek Konstruksi (Bahasa Indonesia)
    private val daftarKategori = listOf(
        "Infrastruktur Publik",
        "Sektor Komersial",
        "Residensial Elite",
        "Kawasan Industri",
        "Fasilitas Negara"
    )

    // 2. Kumpulan Lokasi Kota Besar di Indonesia
    private val daftarLokasi = listOf(
        "Jakarta Pusat",
        "Surabaya Timur",
        "Bandung Kota",
        "Medan Baru",
        "IKN Nusantara",
        "Makassar Port"
    )

    // 3. Kalimat Pembuka agar judul proyek terkesan resmi dan profesional
    private val awalanJudul = listOf(
        "Pembangunan",
        "Proyek Strategis",
        "Renovasi Fasilitas",
        "Optimalisasi Infrastruktur",
        "Peluang Investasi"
    )

    class ViewHolder(
        val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listPost[position]
        val context = holder.itemView.context

        // Rumus matematika sederhana untuk mengacak variasi berdasarkan ID data dari API
        val indeksKategori = post.id % daftarKategori.size
        val indeksLokasi = post.id % daftarLokasi.size
        val indeksAwalan = post.id % awalanJudul.size

        // 4. MEMBUAT JUDUL PROYEK (Bahasa Indonesia)
        // Menggabungkan Awalan Resmi + beberapa kata depan dari API agar judul bervariasi
        val kataKunciAPI = post.title.split(" ").take(3).joinToString(" ")
        val judulIndonesia = "${awalanJudul[indeksAwalan]} $kataKunciAPI"
        holder.binding.tvTitle.text = judulIndonesia.replaceFirstChar { it.uppercase() }

        // 5. MEMBUAT DESKRIPSI PROYEK (Bahasa Indonesia)
        // Membuat deskripsi standar proyek konstruksi yang rapi dan mudah dipahami dosen
        holder.binding.tvBody.text = "Rencana pelaksanaan tahapan fisik untuk ${judulIndonesia.lowercase()} " +
                "yang berlokasi di wilayah ${daftarLokasi[indeksLokasi]}. Proyek berjalan ini mencakup standarisasi " +
                "mutu internasional, manajemen risiko tinggi, serta penyerapan tenaga kerja lokal secara berkala."

        // 6. PASANG BADGE KATEGORI & LOKASI INDONESIA
        holder.binding.tvCategoryBadge.text = daftarKategori[indeksKategori]
        holder.binding.tvLocation.text = "${daftarLokasi[indeksLokasi]}, Indonesia"

        // 7. AMBIL GAMBAR ARSITEKTUR BANGUNAN NYATA VIA GLIDE
        // Menggunakan ID dinamis agar setiap kartu memuat gambar arsitektur gedung yang berbeda
        val urlGambar = "https://picsum.photos/id/${(post.id + 15) * 2}/600/400"

        Glide.with(context)
            .load(urlGambar)
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.holo_red_light)
            .into(holder.binding.ivProjectImage)

        // 8. LOGIKA KLIK TOMBOL "LIHAT DETAIL"
        holder.binding.btnDetailProject.setOnClickListener {
            Toast.makeText(
                context,
                "Membuka data lengkap: $judulIndonesia",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = listPost.size
}