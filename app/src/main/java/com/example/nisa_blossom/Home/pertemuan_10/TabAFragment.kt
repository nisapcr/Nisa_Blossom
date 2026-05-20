package com.example.nisa_blossom.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nisa_blossom.R
import com.example.nisa_blossom.databinding.FragmentTabABinding

class TabAFragment : Fragment() {

    private var _binding: FragmentTabABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set data statistik
        binding.tvProjectCount.text = "48"
        binding.tvContractorCount.text = "32"
        binding.tvCityCount.text = "18"
        binding.tvSuccessRate.text = "98%"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}