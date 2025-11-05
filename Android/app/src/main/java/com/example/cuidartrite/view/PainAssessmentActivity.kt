package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.databinding.ActivityPainAssessmentBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

data class PainData(val date: Date, val value: Float)

class PainAssessmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPainAssessmentBinding

    // Fake source (in real app, troque por dados reais)
    private val allPainData: List<PainData> by lazy { generateFakePainData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPainAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureChartAppearance()
        showLast7Days()

        binding.btnLast7.setOnClickListener { showLast7Days() }
        binding.btnWeek.setOnClickListener { showByWeek() }
        binding.btnMonth.setOnClickListener { showByMonth() }
    }


    // ====== Chart helpers / UI update ========================================================= ||
    private fun configureChartAppearance() {
        binding.lineChart.apply {
            setTouchEnabled(true)
            setPinchZoom(true)
            description.isEnabled = true
            axisRight.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            axisLeft.setDrawGridLines(true)
            legend.isEnabled = true
        }
    }

    private fun updateChart(entries: List<Entry>, labels: List<String>, labelName: String) {
        val dataSet = LineDataSet(entries, labelName).apply {
            lineWidth = 3f
            setDrawCircles(true)
            circleRadius = 5f
            setDrawValues(false)
            mode = LineDataSet.Mode.LINEAR
        }

        val lineData = LineData(dataSet)

        val formatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val i = value.toInt()
                return if (i in labels.indices) labels[i] else ""
            }
        }

        binding.lineChart.apply {
            data = lineData
            description.isEnabled = false

            xAxis.apply {
                valueFormatter = formatter
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawGridLines(false)
            }

            axisRight.isEnabled = false

            axisLeft.apply {
                setDrawGridLines(true)
                axisMinimum = 0f
                axisMaximum = 10f
                granularity = 1f
                setLabelCount(11, true)
            }

            animateX(700)
            invalidate()
        }
    }



    // ====== Views: Últimos 7 dias ============================================================= ||

    private fun showLast7Days() {
        val sdfKey = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        val sdfWeekDay = SimpleDateFormat("EEE", Locale("pt", "BR")) // seg, ter, qua...
        val cal = Calendar.getInstance()

        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()

        val last7Days = (6 downTo 0).map { offset ->
            cal.time = Date()
            cal.add(Calendar.DAY_OF_YEAR, -offset)
            Date(cal.time.time)
        }

        last7Days.forEachIndexed { index, dayDate ->
            val key = sdfKey.format(dayDate)
            val weekdayLabel = sdfWeekDay.format(dayDate).replaceFirstChar { it.uppercaseChar() } // Seg, Ter...


            val valuesToday = allPainData.filter { sdfKey.format(it.date) == key }.map { it.value }

            val avg = if (valuesToday.isNotEmpty()) valuesToday.sum() / valuesToday.size else 0f

            entries.add(Entry(index.toFloat(), avg))
            labels.add(weekdayLabel)
        }

        updateChart(entries, labels, "Últimos 7 dias (média diária)")
    }



    // ====== Agrupar por semana (média) ======================================================== ||

    private fun showByWeek() {

        val locale = Locale.getDefault()
        val calendar = Calendar.getInstance(locale)
        calendar.firstDayOfWeek = Calendar.MONDAY


        val weekMap = mutableMapOf<String, MutableList<PainData>>()

        for (d in allPainData) {
            val c = Calendar.getInstance(locale)
            c.time = d.date
            c.firstDayOfWeek = Calendar.MONDAY
            val year = c.get(Calendar.YEAR)
            val week = c.get(Calendar.WEEK_OF_YEAR)
            val key = "$year-$week"
            weekMap.getOrPut(key) { mutableListOf() }.add(d)
        }

        val weekKeysSorted = weekMap.keys.sortedWith(compareBy { key ->

            val parts = key.split("-")
            val y = parts[0].toInt()
            val w = parts[1].toInt()
            val cal = Calendar.getInstance(locale)

            cal.clear()
            cal.firstDayOfWeek = Calendar.MONDAY
            cal.set(Calendar.YEAR, y)
            cal.set(Calendar.WEEK_OF_YEAR, w)
            cal.timeInMillis
        })

        val labels = ArrayList<String>()
        val entries = ArrayList<Entry>()
        val sdf = SimpleDateFormat("dd/MM", Locale.getDefault())

        var idx = 0
        val last8 = weekKeysSorted.takeLast(8)
        for (key in last8) {
            val list = weekMap[key] ?: continue
            if (list.isEmpty()) continue


            val avg = list.map { it.value }.average().toFloat()


            val sampleCal = Calendar.getInstance(locale)
            val parts = key.split("-")
            val year = parts[0].toInt()
            val week = parts[1].toInt()

            sampleCal.clear()
            sampleCal.firstDayOfWeek = Calendar.MONDAY
            sampleCal.set(Calendar.YEAR, year)
            sampleCal.set(Calendar.WEEK_OF_YEAR, week)

            sampleCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            val start = sampleCal.time

            sampleCal.add(Calendar.DAY_OF_YEAR, 6)


            labels.add(sdf.format(start))
            entries.add(Entry(idx.toFloat(), avg))
            idx++
        }

        if (entries.isEmpty()) {
            binding.lineChart.clear()
            binding.lineChart.invalidate()
            return
        }

        updateChart(entries, labels, "Média por semana")
    }



    // ====== Agrupar por mês (média) =========================================================== ||

    private fun showByMonth() {
        val locale = Locale("pt", "BR")
        val cal = Calendar.getInstance(locale)
        val monthMap = mutableMapOf<String, MutableList<PainData>>()

        for (d in allPainData) {
            val c = Calendar.getInstance(locale)
            c.time = d.date
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val key = "$year-$month"
            monthMap.getOrPut(key) { mutableListOf() }.add(d)
        }

        val monthKeysSorted = monthMap.keys.sortedWith(compareBy { key ->
            val (y, m) = key.split("-").map { it.toInt() }
            Calendar.getInstance(locale).apply {
                clear()
                set(Calendar.YEAR, y)
                set(Calendar.MONTH, m)
            }.timeInMillis
        })

        val last8 = monthKeysSorted.takeLast(8)
        val labels = ArrayList<String>()
        val entries = ArrayList<Entry>()

        var idx = 0
        for (key in last8) {
            val list = monthMap[key] ?: continue
            if (list.isEmpty()) continue

            val avg = list.map { it.value }.average().toFloat()

            val (y, m) = key.split("-").map { it.toInt() }

            cal.clear()
            cal.set(Calendar.YEAR, y)
            cal.set(Calendar.MONTH, m)

            val monthLabel = SimpleDateFormat("LLL/yy", locale)
                .format(cal.time)
                .replaceFirstChar { it.lowercase() }

            labels.add(monthLabel)
            entries.add(Entry(idx.toFloat(), avg))
            idx++
        }

        if (entries.isEmpty()) {
            binding.lineChart.clear()
            binding.lineChart.invalidate()
            return
        }

        updateChart(entries, labels, "Média por mês")
    }







    // Fake data generator (últimos 90 dias, aleatório)

    private fun generateFakePainData(): List<PainData> {
        val list = mutableListOf<PainData>()
        val cal = Calendar.getInstance()
        // gerar dados dos últimos 90 dias com alguns dias sem registro
        for (i in 0..89) {
            val chanceHasData = (Math.random() > 0.3) // ~70% chance de ter dado naquele dia
            if (chanceHasData) {
                cal.time = Date()
                cal.add(Calendar.DAY_OF_YEAR, -i)
                val value = (Math.random() * 10).toFloat() // escala 0..10
                list.add(PainData(cal.time, (value * 10).roundToInt() / 10f)) // round 1 decimal
            }
        }
        // pode embaralhar (não obrigatório)
        list.sortBy { it.date.time } // garantir ordem cronológica
        return list
    }
}
