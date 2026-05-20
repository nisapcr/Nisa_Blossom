package com.example.nisa_blossom.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nisa_blossom.R
import com.example.nisa_blossom.databinding.FragmentTabCBinding


class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel("Perumahan Modern Elite", "Rp 850 Jt", "https://picsum.photos/seed/house1/400/300", "Jakarta"),
        ProductModel("Gedung Perkantoran Smart", "Rp 320 M", "https://picsum.photos/seed/office1/400/300", "Surabaya"),
        ProductModel("Jembatan Layang Modern", "Rp 450 M", "https://picsum.photos/seed/bridge1/400/300", "Bandung"),
        ProductModel("Ruang Publik Terpadu", "Rp 120 M", "https://picsum.photos/seed/park1/400/300", "Medan"),
        ProductModel("Apartemen Hijau", "Rp 980 M", "https://picsum.photos/seed/apartment1/400/300", "Tangerang"),
        ProductModel("Flyover Sudirman", "Rp 280 M", "https://picsum.photos/seed/flyover1/400/300", "Jakarta"),
        ProductModel("Kawasan Industri", "Rp 1.2 T", "https://picsum.photos/seed/industry1/400/300", "Karawang"),
        ProductModel("Sport Center", "Rp 350 M", "https://picsum.photos/seed/stadium1/400/300", "Palembang"),
        ProductModel("Rumah Sakit Modern", "Rp 560 M", "https://picsum.photos/seed/hospital1/400/300", "Semarang"),
        ProductModel("Sekolah Digital", "Rp 95 M", "https://picsum.photos/seed/school1/400/300", "Yogyakarta"),
        ProductModel("Mall Terintegrasi", "Rp 780 M", "https://picsum.photos/seed/mall1/400/300", "Surabaya"),
        ProductModel("Taman Kota", "Rp 45 M", "https://picsum.photos/seed/garden1/400/300", "Bandung"),
        ProductModel("Underpass Thamrin", "Rp 210 M", "https://picsum.photos/seed/tunnel1/400/300", "Jakarta"),
        ProductModel("Rusunawa", "Rp 175 M", "https://picsum.photos/seed/flat1/400/300", "Bekasi"),
        ProductModel("Bandara Baru", "Rp 3.2 T", "https://picsum.photos/seed/airport1/400/300", "Lombok"),
        ProductModel("Terminal Bus", "Rp 95 M", "https://picsum.photos/seed/busstop1/400/300", "Bogor"),
        ProductModel("Hotel Bintang 5", "Rp 890 M", "https://picsum.photos/seed/hotel1/400/300", "Bali"),
        ProductModel("Pasar Modern", "Rp 65 M", "https://picsum.photos/seed/market1/400/300", "Malang")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "📍 ${selectedItem.name} - ${selectedItem.location}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}