package com.example.cuidartrite.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityProgressAndSensationDiaryBinding
import com.example.cuidartrite.network.api.controller.ApiTecnicaController
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheRequest
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose
import com.example.cuidartrite.network.models.User
import com.example.cuidartrite.view.adapter.ProgressHistoryAdapter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
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

                Log.d("ProgressSensationDetailActivity", response.toString())

                val adapter = ProgressHistoryAdapter(response)
                binding.recyclerTabela.layoutManager = LinearLayoutManager(this@ProgressSensationDetailActivity)
                binding.recyclerTabela.adapter = adapter

                setupChart(response)
            }
        }
    }

    private fun setupChart(lista: List<HistoricoTecnicaDetalheResponse>) {
        val sortedList = lista.sortedBy { it.data }
        val entries = sortedList.mapIndexed { index, item ->
            Entry(index.toFloat(), item.nivelDorAntes.toFloat())
        }

        val dataSet = LineDataSet(entries, "Nível de dor").apply {
            lineWidth = 2f
            circleRadius = 4f
            setDrawValues(false)
            setDrawFilled(true)
        }

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        // Configuração eixo X (datas)
        val dates = formatDate(sortedList)

        binding.lineChart.xAxis.apply {
            granularity = 1f
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(dates)
            textSize = 10f
        }

        // Eixo Y
        binding.lineChart.axisLeft.apply {
            axisMinimum = 0f
            axisMaximum = 10f
            granularity = 1f
            textSize = 12f
        }

        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.animateX(800)
        binding.lineChart.invalidate()
    }

    private fun formatDate(sortedList: List<HistoricoTecnicaDetalheResponse>): List<String> {
        return sortedList.map { item ->
            try {
                val parts = item.data.split("-")
                "${parts[2]}/${parts[1]}"
            } catch (e: Exception) {
                item.data
            }
        }
    }
}