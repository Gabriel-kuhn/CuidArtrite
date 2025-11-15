package com.example.cuidartrite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.databinding.ItemTechiniqueResumeBinding
import com.example.cuidartrite.network.models.TecnicaDetalheResponse

class ExerciseListAdapter(
    private val items: List<TecnicaDetalheResponse>,
    private val onItemClick: (TecnicaDetalheResponse) -> Unit
) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTechiniqueResumeBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TecnicaDetalheResponse) {
            binding.tvBreathingTechniques.text = item.titulo
            binding.tvBenefitsTime.text = item.beneficios
            binding.tvDurationTime.text = "${item.quantoTempo} minutos"

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
