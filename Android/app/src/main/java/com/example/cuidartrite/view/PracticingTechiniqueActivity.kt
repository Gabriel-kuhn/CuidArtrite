package com.example.cuidartrite.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.cuidartrite.R
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityPracticingTechiniqueBinding
import com.example.cuidartrite.databinding.BottomsheetDorInicialBinding
import com.example.cuidartrite.databinding.BottomsheetFinalizarPraticaBinding
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.User
import com.google.android.material.bottomsheet.BottomSheetDialog

class PracticingTechiniqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticingTechiniqueBinding
    private lateinit var user: User
    private lateinit var exercise: TecnicaDetalheResponse

    private var nivelDorAntes: Int? = null
    private var nivelDorDepois: Int? = null
    private var sensacao: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticingTechiniqueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra(EXTRA_USER)!!
        exercise = intent.getParcelableExtra(EXTRA_EXERCISE)!!

        binding.tvBeneficios.text = exercise.beneficios
        binding.tvComoFazer.text = exercise.comoFazer
        binding.tvDica.text = exercise.dica
        binding.tvTitle.text = exercise.titulo

        binding.btnStartFinish.text = "Iniciar prática"
        binding.btnStartFinish.setOnClickListener {
            if (nivelDorAntes == null)
                abrirBottomSheetDorInicial()
            else
                abrirBottomSheetFinalizar()
        }
    }

    private fun abrirBottomSheetDorInicial() {
        val dialog = BottomSheetDialog(this)

        val sheetBinding = BottomsheetDorInicialBinding.inflate(layoutInflater)
        dialog.setContentView(sheetBinding.root)

        sheetBinding.btnConfirmarDorInicial.setOnClickListener {
            nivelDorAntes = sheetBinding.sliderDorInicial.value.toInt()

            dialog.dismiss()
            binding.btnStartFinish.text = "Finalizar prática"
            binding.btnStartFinish.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FF5722"))
            binding.btnStartFinish.icon = AppCompatResources.getDrawable(this, R.drawable.ic_stop)
        }

        dialog.show()
    }

    private fun abrirBottomSheetFinalizar() {
        val dialog = BottomSheetDialog(this)

        val sheetBinding = BottomsheetFinalizarPraticaBinding.inflate(layoutInflater)
        dialog.setContentView(sheetBinding.root)

        sheetBinding.btnConfirmarFinal.setOnClickListener {
            nivelDorDepois = sheetBinding.sliderDorFinal.value.toInt()
            sensacao = sheetBinding.etSensacao.text.toString()

            if (sensacao.isNullOrBlank()) {
                sheetBinding.etSensacao.error = "Descreva a sensação"
                return@setOnClickListener
            }

            dialog.dismiss()
            enviarRegistroParaBackend()
        }

        dialog.show()
    }

    private fun enviarRegistroParaBackend() {

        
        Toast.makeText(this, "Prática registrada com sucesso!", Toast.LENGTH_LONG).show()

        finish()
    }
}