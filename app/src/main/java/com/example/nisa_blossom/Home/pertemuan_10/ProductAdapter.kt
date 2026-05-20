package com.example.nisa_blossom.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nisa_blossom.R
import com.example.nisa_blossom.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = product.price
            tvLocation.text = product.location

            // Load gambar menggunakan Glide
            Glide.with(root.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_placeholder)  // ← Buat file ini
                .error(R.drawable.ic_placeholder)        // ← Buat file ini
                .into(imgProduct)

            root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}