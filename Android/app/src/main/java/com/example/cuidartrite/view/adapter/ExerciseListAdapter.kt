package com.example.cuidartrite.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cuidartrite.R
import com.example.cuidartrite.databinding.ItemTechiniqueResumeBinding
import com.example.cuidartrite.network.models.TecnicaDetalheResponse

class ExerciseListAdapter(
    private val items: List<TecnicaDetalheResponse>,
    private val onItemClick: (TecnicaDetalheResponse) -> Unit
) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTechiniqueResumeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TecnicaDetalheResponse) {
            binding.tvBreathingTechniques.text = item.titulo
            binding.tvBenefitsTime.text = item.beneficios
            binding.tvDurationTime.text = item.quantoTempo

            try {
                val videoId = item.videoUrl
                val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

                Glide.with(binding.root.context)
                    .load(thumbnailUrl)
                    .placeholder(R.drawable.ic_stretching)
                    .centerCrop()
                    .into(binding.ivThumbnail)

            } catch (e: Exception) {
                Log.e("ThumbnailUrl", e.toString())
            }

            binding.cvBreathingTechniques.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTechiniqueResumeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
