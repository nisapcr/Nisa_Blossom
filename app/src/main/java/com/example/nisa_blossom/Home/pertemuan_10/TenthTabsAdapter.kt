package com.example.nisa_blossom.Home.pertemuan_10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nisa_blossom.R

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()  // Info Proyek
            1 -> TabBFragment()  // Tim Kami
            2 -> TabCFragment()  // Portfolio (RecyclerView)
            else -> TabAFragment()
        }
    }
}