package com.example.nisa_blossom.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nisa_blossom.R
import com.example.nisa_blossom.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Setup adapter
        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Info Proyek"
                    tab.setIcon(R.drawable.ic_project_info)
                }

                1 -> {
                    tab.text = "Testimoni"
                    tab.setIcon(R.drawable.ic_team)
                }

                2 -> {
                    tab.text = "Portfolio"
                    tab.setIcon(R.drawable.ic_portfolio)
                }
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}