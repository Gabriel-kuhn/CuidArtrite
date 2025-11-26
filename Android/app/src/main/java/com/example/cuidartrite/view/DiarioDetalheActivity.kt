package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_DAIRY
import com.example.cuidartrite.databinding.ActivityDiarioDetalheBinding
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse

class DiarioDetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiarioDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiarioDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historico = intent.getParcelableExtra<HistoricoTecnicaDetalheResponse>(EXTRA_DAIRY)
            ?: return

        // Título da técnica
        binding.tvTitulo.text = historico.tituloTecnica

        // Data formatada
        binding.tvData.text = formatDate(historico.data)

        // Sliders
        binding.sliderAntes.value = historico.nivelDorAntes.toFloat()
        binding.sliderDepois.value = historico.nivelDorDepois.toFloat()

        binding.sliderAntes.isEnabled = false
        binding.sliderDepois.isEnabled = false

        // Sensação
        binding.tvSensacao.text = historico.sensacao
    }

    private fun formatDate(date: String): String {
        return try {
            val parts = date.split("-")
            val dia = parts[2]
            val mes = parts[1].toInt()

            val nomeMes = arrayOf(
                "JAN", "FEV", "MAR", "ABR", "MAI", "JUN",
                "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"
            )[mes - 1]

            "$dia $nomeMes ${parts[0]}"
        } catch (e: Exception) {
            date
        }
    }
}
