package com.example.cuidartrite.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.R
import com.example.cuidartrite.databinding.ProgressAndSensationDailyItemBinding
import com.example.cuidartrite.view.RegistroDor

class ProgressSensationAdapter(
    private val lista: List<RegistroDor>,
    private val onVerMaisClick: (RegistroDor) -> Unit
) : RecyclerView.Adapter<ProgressSensationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProgressAndSensationDailyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProgressAndSensationDailyItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        val context = holder.itemView.context

        with(holder.binding) {
            val backgroundColor = getBackGroundColor(position)

            root.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            tvNome.text = item.nome
            tvVezes.text = item.vezes.toString()
            tvUltimaVez.text = item.ultimaVez

            btnDetalhes.setOnClickListener { onVerMaisClick(item) }
        }
    }

    override fun getItemCount() = lista.size

    private fun getBackGroundColor(position: Int): Int {
        return if (position % 2 == 0)
            R.color.white
        else
            R.color.grey
    }
}
