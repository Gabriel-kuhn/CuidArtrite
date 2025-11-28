package com.example.cuidartrite.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.R
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityPracticingTechiniqueBinding
import com.example.cuidartrite.databinding.BottomsheetDorInicialBinding
import com.example.cuidartrite.databinding.BottomsheetFinalizarPraticaBinding
import com.example.cuidartrite.network.api.controller.ApiTecnicaController
import com.example.cuidartrite.network.models.RegistrarAtividadeRequest
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.User
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PracticingTechiniqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticingTechiniqueBinding
    private lateinit var user: User
    private lateinit var exercise: TecnicaDetalheResponse
    private lateinit var youtubePlayerView: YouTubePlayerView

    private var nivelDorAntes: Int? = null
    private var nivelDorDepois: Int? = null
    private lateinit var sensacao: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticingTechiniqueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra(EXTRA_USER)!!
        exercise = intent.getParcelableExtra(EXTRA_EXERCISE)!!

        Log.d("PracticingTechiniqueActivity", "exercise: $exercise  user: $user")

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

        setupYouTubePlayer()
    }

    private fun setupYouTubePlayer() {
        youtubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = exercise.videoUrl!!

                //youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
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
        val request = RegistrarAtividadeRequest(
            user.id!!,
            exercise.id,
            nivelDorAntes ?: 0,
            nivelDorDepois ?: 0,
            sensacao
        )

        lifecycleScope.launch {
            ApiTecnicaController().registrarAtividade(request)

            Toasty.success(
                this@PracticingTechiniqueActivity,
                "Prática registrada com sucesso!",
                Toasty.LENGTH_LONG,
                true
            ).show()

            delay(1000)
        }
        finish()
    }
}