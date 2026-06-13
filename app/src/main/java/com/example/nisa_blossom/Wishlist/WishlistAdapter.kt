package com.example.nisa_blossom.Wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nisa_blossom.Data.entity.WishlistEntity
import com.example.nisa_blossom.databinding.ItemWishlistBinding

class WishlistAdapter(
    private val listWishlist: MutableList<WishlistEntity>,
    private val onDelete: (WishlistEntity) -> Unit,
    private val onEdit: (WishlistEntity) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemWishlistBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = listWishlist[position]

        holder.binding.tvFlowerName.text = item.flowerName
        holder.binding.tvPrice.text = item.price
        holder.binding.tvDescription.text = item.description

        holder.binding.btnDelete.setOnClickListener {
            onDelete(item)
        }

        holder.binding.btnEdit.setOnClickListener {
            onEdit(item)
        }
    }

    override fun getItemCount(): Int = listWishlist.size
}