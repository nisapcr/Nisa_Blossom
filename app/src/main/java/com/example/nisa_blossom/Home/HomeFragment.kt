package com.example.nisa_blossom.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nisa_blossom.AuthActivity
import com.example.nisa_blossom.Home.pertemuan_10.TenthActivity
import com.example.nisa_blossom.Home.pertemuan_3.LoginActivity
import com.example.nisa_blossom.Home.pertemuan_4.Custom_1Activity
import com.example.nisa_blossom.Home.pertemuan_4.Custom_2Activity
import com.example.nisa_blossom.Home.pertemuan_4.DashboardActivity
import com.example.nisa_blossom.Home.pertemuan_6.WebView_Activity
import com.example.nisa_blossom.Home.pertemuan_9.NinthActivity
import com.example.nisa_blossom.Home.pertemuan_9.settings.SettingsActivity
import com.example.nisa_blossom.databinding.FragmentHomeBinding
import com.example.nisa_blossom.pertemuan_2.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil username dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        // Set Welcome Text
        binding.txtWelcome.text = "Welcome, $username 👋"

        // Tombol Pertemuan 2
        binding.btnToSecond.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        // Tombol Pertemuan 3 (Login)
        binding.btnToThird.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        // Tombol Pertemuan 4 (Dashboard)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // Tombol Custom 1
        binding.btnCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom_1Activity::class.java))
        }

        // Tombol Custom 2
        binding.btnCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), Custom_2Activity::class.java))
        }

        // Tombol WebView
        binding.btnWeb.setOnClickListener {
            startActivity(Intent(requireContext(), WebView_Activity::class.java))
        }

        // Tombol Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Tombol Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Tombol Settings
        binding.btnSettings.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                    Log.d("Logout", "Berhasil logout")
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}