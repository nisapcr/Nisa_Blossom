package com.example.nisa_blossom.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nisa_blossom.Data.entity.NoteEntity
import com.example.nisa_blossom.databinding.ItemNoteBinding

class NoteAdapter(
    private val listNote: MutableList<NoteEntity>,
    private val onDelete: (NoteEntity) -> Unit,
    private val onEdit: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // <-- Buka Kurung onBindViewHolder

        val note = listNote[position] // <-- Variabel note dibuat di sini

        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        // PASTIKAN tombol-tombol ini ada DI DALAM kurung sebelum tutup kurung onBindViewHolder
        holder.binding.btnDelete.setOnClickListener {
            onDelete(note) // <-- Di sini note PASTI aman (tidak merah)
        }

        holder.binding.btnEdit.setOnClickListener {
            onEdit(note) // <-- Di sini note PASTI aman (tidak merah)
        }

    } // <-- Tutup Kurung onBindViewHolder

    override fun getItemCount(): Int = listNote.size
}
