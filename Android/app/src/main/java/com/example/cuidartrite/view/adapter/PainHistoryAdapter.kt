package com.example.cuidartrite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.databinding.ItemPainHistoryBinding
import com.example.cuidartrite.view.PainData
import java.text.SimpleDateFormat
import java.util.Locale

class PainHistoryAdapter(
    private val list: List<PainData>
) : RecyclerView.Adapter<PainHistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPainHistoryBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPainHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.tvItemDate.text = sdf.format(item.date)
        holder.binding.tvItemPain.text = "Dor: ${item.value.toInt()}/10"
    }
}
