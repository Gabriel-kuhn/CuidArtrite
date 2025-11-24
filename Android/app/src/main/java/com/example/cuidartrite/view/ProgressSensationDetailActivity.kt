package com.example.cuidartrite.view

import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityProgressAndSensationDiaryBinding
import com.example.cuidartrite.network.api.controller.ApiTecnicaController
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheRequest
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose
import com.example.cuidartrite.network.models.User
import com.example.cuidartrite.view.adapter.ProgressHistoryAdapter
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgressSensationDetailActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var exercise: TecnicaResumidaDetalheRespose
    private lateinit var binding: ActivityProgressAndSensationDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressAndSensationDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exercise = intent.extras!!.getParcelable(EXTRA_EXERCISE)!!
        user = intent.extras!!.getParcelable(EXTRA_USER)!!

        binding.tvTitle.text = exercise.titulo

        lifecycleScope.launch(Dispatchers.IO) {

            val request = HistoricoTecnicaDetalheRequest(
                userId = user.id!!,
                techniqueId = exercise.id
            )

            Log.d("Progress Sensation Detail Activity", request.toString())

            val response = ApiTecnicaController().getHistoricoTecnica(request)

            withContext(Dispatchers.Main) {

                if (response == null) {
                    Toasty.error(
                        this@ProgressSensationDetailActivity,
                        "Falha ao carregar histórico",
                        Toasty.LENGTH_SHORT,
                        true
                    ).show()
                    return@withContext
                }

                if (response.isEmpty()) {
                    //binding.tvWarningEmpty.visibility = VISIBLE
                    return@withContext
                }

                val adapter = ProgressHistoryAdapter(response)
                binding.recyclerTabela.adapter = adapter
            }
        }
    }
}