package com.example.cuidartrite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.databinding.ProgressHistoryItemBinding
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse

class ProgressHistoryAdapter(
    private val items: List<HistoricoTecnicaDetalheResponse>
) : RecyclerView.Adapter<ProgressHistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProgressHistoryItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoricoTecnicaDetalheResponse) {
            binding.tvUltimaVez.text = item.data ?: "--"
            //binding.btnDetalhes.text = item.sensacao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProgressHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
