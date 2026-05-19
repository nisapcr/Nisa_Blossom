package com.example.nisa_blossom.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nisa_blossom.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "About"

        setupGridMenu()
    }

    private fun setupGridMenu() {
        // Data dengan tema proyek konstruksi/pembangunan fisik
        val privacyData = listOf(
            "🏗️ Data proyek konstruksi bersifat rahasia",
            "📋 Dokumen tender proyek hanya untuk internal",
            "🔒 RAB dan spesifikasi teknis dilindungi",
            "🤝 Kerjasama kontraktor bersifat rahasia"
        )

        val termsData = listOf(
            "📐 Patuhi standar konstruksi SNI",
            "🚧 K3 (Keselamatan dan Kesehatan Kerja) wajib diterapkan",
            "📅 Pekerjaan sesuai jadwal proyek",
            "💰 Pembayaran sesuai progres fisik"
        )

        val helpData = listOf(
            "📞 Call Center Proyek: (0761) 1234567",
            "📧 Email: proyek@binadesa.com",
            "🕐 Jam kerja: Senin-Jumat, 07.00 - 17.00 WIB",
            "🏗️ Kantor: Site Office Proyek Bina Desa"
        )

        val versionData = listOf(
            "📱 Aplikasi Manajemen Proyek: v1.0.0",
            "📅 Rilis: Mei 2026",
            "🔧 Dikembangkan untuk proyek konstruksi",
            "🎯 Target: Mengelola 50 proyek pembangunan desa"
        )

        val devsData = listOf(
            "👷 Siti Harnisa Nurhabiby - Project Manager",
            "🏗️ Tim Teknis - Konsultan Konstruksi",
            "🤝 Kontraktor Mitra: PT Bangun Desa",
            "📐 Pengawas Proyek: Dinas PUPR"
        )

        val ossData = listOf(
            "📊 Glide - Dokumentasi progres proyek",
            "🎨 Material Design - Monitoring lapangan",
            "📐 GridLayout - Laporan mingguan proyek",
            "🏗️ Bina Desa - Owner/Pemberi Tugas"
        )

        binding.btnPrivacy.setOnClickListener {
            showListView("🔒 Kebijakan Data Proyek", privacyData)
        }
        binding.btnTerms.setOnClickListener {
            showListView("📜 Standar Prosedur Proyek", termsData)
        }
        binding.btnHelp.setOnClickListener {
            showListView("🆘 Bantuan Teknis Lapangan", helpData)
        }
        binding.btnVersion.setOnClickListener {
            showListView("ℹ️ Versi Aplikasi Proyek", versionData)
        }
        binding.btnDevelopers.setOnClickListener {
            showListView("👷 Tim Manajemen Proyek", devsData)
        }
        binding.btnOpenSource.setOnClickListener {
            showListView("📚 Teknologi Pendukung Proyek", ossData)
        }
    }
    private fun showListView(title: String, dataList: List<String>) {
        binding.tvDetailTitle.text = title
        binding.tvDetailTitle.visibility = View.VISIBLE
        binding.listViewDetail.visibility = View.VISIBLE

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            dataList
        )
        binding.listViewDetail.adapter = adapter

        // Scroll ke ListView
        binding.listViewDetail.smoothScrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}