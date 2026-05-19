package com.example.nisa_blossom.Home.pertemuan_9.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nisa_blossom.R
import com.google.android.material.card.MaterialCardView

class SettingsAdapter(
    private val settingsList: List<SettingsModel>,
    private val onItemClick: (SettingsModel) -> Unit,
    private val onSwitchChange: ((SettingsModel, Boolean) -> Unit)? = null
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val setting = settingsList[position]
        holder.bind(setting)
    }

    override fun getItemCount(): Int = settingsList.size

    inner class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardSettings)
        private val tvIcon: TextView = itemView.findViewById(R.id.tvIcon)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val switchCompat: SwitchCompat = itemView.findViewById(R.id.switchCompat)

        fun bind(setting: SettingsModel) {
            // Set icon (bisa emoji atau drawable)
            if (setting.icon.startsWith("emoji_")) {
                tvIcon.text = when (setting.icon) {
                    "emoji_notification" -> "🔔"
                    "emoji_language" -> "🌐"
                    "emoji_privacy" -> "🔒"
                    "emoji_help" -> "❓"
                    "emoji_about" -> "ℹ️"
                    "emoji_dark" -> "🌙"
                    else -> "⚙️"
                }
            } else {
                // Jika pakai drawable
                val drawableId = itemView.context.resources.getIdentifier(
                    setting.icon, "drawable", itemView.context.packageName
                )
                if (drawableId != 0) {
                    tvIcon.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0)
                }
            }

            tvTitle.text = setting.title
            tvDescription.text = setting.description

            // Handle switch visibility
            if (setting.hasSwitch) {
                switchCompat.visibility = View.VISIBLE
                switchCompat.isChecked = false
                switchCompat.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                    onSwitchChange?.invoke(setting, isChecked)
                }
            } else {
                switchCompat.visibility = View.GONE
            }

            // Handle click
            cardView.setOnClickListener {
                onItemClick(setting)
            }
        }
    }
}