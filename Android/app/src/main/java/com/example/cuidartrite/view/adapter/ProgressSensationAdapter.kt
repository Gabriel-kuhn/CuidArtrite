package com.example.cuidartrite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidartrite.R
import com.example.cuidartrite.databinding.ProgressAndSensationDailyItemBinding
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose

class ProgressSensationAdapter(
    private val lista: List<TecnicaResumidaDetalheRespose>?,
    private val onVerMaisClick: (TecnicaResumidaDetalheRespose) -> Unit
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
        lista?.let {
            val item = lista[position]
            val context = holder.itemView.context

            with(holder.binding) {
                val backgroundColor = getBackGroundColor(position)

                root.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
                tvNome.text = item.titulo
                tvVezes.text = item.vezesPraticada.toString()
                tvUltimaVez.text = formatarDataHora(item.ultimaVezPraticada)

                btnDetalhes.setOnClickListener { onVerMaisClick(item) }
            }
        }
    }

    override fun getItemCount() = lista?.size ?: 0

    private fun getBackGroundColor(position: Int): Int {
        return if (position % 2 == 0)
            R.color.white
        else
            R.color.grey
    }

    private fun formatarDataHora(isoString: String): String {
        return try {
            val inputFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
            val data = inputFormat.parse(isoString)

            val outputFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
            outputFormat.format(data!!)
        } catch (e: Exception) {
            isoString
        }
    }

}
