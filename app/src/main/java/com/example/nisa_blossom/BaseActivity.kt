package com.example.nisa_blossom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nisa_blossom.Home.HomeFragment
import com.example.nisa_blossom.Note.NoteFragment
import com.example.nisa_blossom.Wishlist.fragment_wishlist
import com.example.nisa_blossom.about.AboutFragment
import com.example.nisa_blossom.databinding.ActivityBaseBinding
import com.example.nisa_blossom.profile.ProfileFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.note -> {
                    replaceFragment(NoteFragment())
                    true
                }

                R.id.wishlist -> {
                    replaceFragment(fragment_wishlist())
                    true
                }

                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }

                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}