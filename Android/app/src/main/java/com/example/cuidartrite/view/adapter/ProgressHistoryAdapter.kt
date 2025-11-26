package com.example.cuidartrite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.R
import com.example.cuidartrite.databinding.ProgressHistoryItemBinding
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse

class ProgressHistoryAdapter(
    private val items: List<HistoricoTecnicaDetalheResponse>,
    private val onItemClick: (HistoricoTecnicaDetalheResponse) -> Unit
) : RecyclerView.Adapter<ProgressHistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProgressHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoricoTecnicaDetalheResponse) {
            binding.tvUltimaVez.text = item.data ?: "--"
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
        val item = items[position]
        holder.bind(item)

        val context = holder.binding.root.context
        val color = if (position % 2 == 0)
            context.getColor(R.color.white)
        else
            context.getColor(R.color.grey)
        holder.binding.root.setBackgroundColor(color)

        holder.binding.btnDetalhes.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}

