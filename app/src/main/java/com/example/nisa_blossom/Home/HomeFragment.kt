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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisa_blossom.AuthActivity
import com.example.nisa_blossom.Data.Api.PostApiClient
import com.example.nisa_blossom.Data.Model.PostModel
import com.example.nisa_blossom.Home.Berita.PostAdapter
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref =
            requireContext().getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )

        val username =
            sharedPref.getString(
                "username",
                "User"
            )

        // Toolbar
        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "Home"

        // Welcome Text
        binding.txtWelcome.text =
            "Welcome, $username 👋"

        // Pertemuan 2 (Kalkulator)
        binding.btnToSecond.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MainActivity::class.java
                )
            )
        }

        // Pertemuan 3
        binding.btnToThird.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    LoginActivity::class.java
                )
            )
        }

        // Pertemuan 4
        binding.btnToFourth.setOnClickListener {

            val intent =
                Intent(
                    requireContext(),
                    DashboardActivity::class.java
                )

            intent.putExtra(
                "USERNAME",
                username
            )

            startActivity(intent)
        }

        // Custom 1
        binding.btnCustom1.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    Custom_1Activity::class.java
                )
            )
        }

        // Custom 2
        binding.btnCustom2.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    Custom_2Activity::class.java
                )
            )
        }

        // WebView
        binding.btnWeb.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    WebView_Activity::class.java
                )
            )
        }

        // Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NinthActivity::class.java
                )
            )
        }

        // Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    TenthActivity::class.java
                )
            )
        }

        // Settings
        binding.btnSettings.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    SettingsActivity::class.java
                )
            )
        }

        // Logout
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    startActivity(
                        Intent(
                            requireContext(),
                            AuthActivity::class.java
                        )
                    )

                    requireActivity().finish()

                    Log.d(
                        "Logout",
                        "Berhasil logout"
                    )
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Load Berita API
        getPosts()
    }

    private fun getPosts() {

        PostApiClient.instance
            .getPosts()
            .enqueue(object : Callback<List<PostModel>> {

                override fun onResponse(
                    call: Call<List<PostModel>>,
                    response: Response<List<PostModel>>
                ) {

                    if (response.isSuccessful) {

                        val data =
                            response.body()
                                ?: emptyList()

                        binding.rvPost.layoutManager =
                            LinearLayoutManager(
                                requireContext()
                            )

                        binding.rvPost.adapter =
                            PostAdapter(data)
                    }
                }

                override fun onFailure(
                    call: Call<List<PostModel>>,
                    t: Throwable
                ) {

                    Log.e(
                        "API_ERROR",
                        t.message.toString()
                    )
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
