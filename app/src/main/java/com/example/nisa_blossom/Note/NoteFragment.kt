package com.example.nisa_blossom.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisa_blossom.Data.AppDatabase
import com.example.nisa_blossom.Data.entity.NoteEntity
import com.example.nisa_blossom.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter

    private val listNote = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(
            inflater,
            container,
            false
        )

        db = AppDatabase.getInstance(requireContext())

        // UPDATE: Inisialisasi adapter dengan onDelete dan onEdit
        adapter = NoteAdapter(
            listNote,
            onDelete = { note ->
                lifecycleScope.launch {
                    db.noteDao().delete(note)
                    loadData() // Memuat ulang data setelah dihapus
                }
            },
            onEdit = { note ->
                // Mengirim data ke NoteFormActivity untuk diedit
                val intent = Intent(requireContext(), NoteFormActivity::class.java)
                intent.putExtra("id", note.id)
                intent.putExtra("title", note.title)
                intent.putExtra("content", note.content)
                startActivity(intent)
            }
        )

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NoteFormActivity::class.java
                )
            )
        }

        loadData()

        return binding.root
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll()
            listNote.clear()
            listNote.addAll(data)
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