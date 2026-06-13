package com.example.nisa_blossom.Wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisa_blossom.Data.AppDatabase
import com.example.nisa_blossom.Data.entity.WishlistEntity
import com.example.nisa_blossom.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class fragment_wishlist : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: WishlistAdapter

    private val listWishlist = mutableListOf<WishlistEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWishlistBinding.inflate(
            inflater,
            container,
            false
        )

        db = AppDatabase.getInstance(requireContext())

        // UPDATE: Inisialisasi adapter dengan callback onDelete dan onEdit
        adapter = WishlistAdapter(
            listWishlist,
            onDelete = { item ->
                lifecycleScope.launch {
                    db.wishlistDao().delete(item)
                    loadData() // Muat ulang data RecyclerView setelah dihapus
                }
            },
            onEdit = { item ->
                // Pindah ke WishlistFormActivity membawa data lama untuk diedit
                val intent = Intent(requireContext(), WishlistFormActivity::class.java)
                intent.putExtra("id", item.id)
                intent.putExtra("flowerName", item.flowerName)
                intent.putExtra("price", item.price)
                intent.putExtra("description", item.description)
                startActivity(intent)
            }
        )

        binding.rvWishlist.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWishlist.adapter = adapter

        binding.fabAddWishlist.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    WishlistFormActivity::class.java
                )
            )
        }

        loadData()

        return binding.root
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.wishlistDao().getAll()
            listWishlist.clear()
            listWishlist.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}