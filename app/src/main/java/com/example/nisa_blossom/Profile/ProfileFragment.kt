package com.example.nisa_blossom.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nisa_blossom.R
import com.example.nisa_blossom.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Profile"

        setupSocialLinks()
        setupChipGroup()
    }

    private fun setupSocialLinks() {
        // Instagram
        binding.icInstagram.setOnClickListener {
            openUrl("https://www.instagram.com/siti.harnisanr?igsh=N3ZkM25kanMzbzYx")
        }

        // GitHub
        binding.icGithub.setOnClickListener {
            openUrl("https://github.com/nisapcr/Nisa_Blossom.git")
        }

        // LinkedIn
        binding.icLinkedin.setOnClickListener {
            openUrl("https://www.linkedin.com/in/siti-harnisa-nurhabiby-90115a39b")
        }
    }

    private fun setupChipGroup() {
        // ChipGroup Bidang Pekerjaan Proyek Konstruksi
        binding.chipGroupMinat.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedMinat = checkedIds.map { id ->
                when (id) {
                    R.id.chipPertanian -> "Pekerjaan Tanah & Land Clearing"
                    R.id.chipPeternakan -> "Pembangunan Drainase & Irigasi"
                    R.id.chipPerikanan -> "Pekerjaan Beton & Struktur"
                    R.id.chipUKM -> "Pengadaan Material Proyek"
                    R.id.chipEdukasi -> "Dokumentasi & Pelaporan"
                    R.id.chipKesehatan -> "K3 & Kesehatan Kerja"
                    else -> ""
                }
            }.filter { it.isNotEmpty() }

            if (selectedMinat.isNotEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Bidang proyek: ${selectedMinat.joinToString(", ")}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}