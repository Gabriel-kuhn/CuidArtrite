package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_TECHINIQUE_TYPE_ID
import com.example.cuidartrite.databinding.ActivityReliefTechiniquesBinding
import com.example.cuidartrite.enums.TechiniqueType
import com.example.cuidartrite.view.adapter.ProgressSensationAdapter

class ReliefTechiniquesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReliefTechiniquesBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReliefTechiniquesBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val lista = listOf(
            RegistroDor("Joelho", 3, "10/11/2025"),
            RegistroDor("Ombro", 1, "08/11/2025"),
            RegistroDor("Costas", 5, "12/11/2025")
        )

        binding.recyclerTabela.apply {
            layoutManager = LinearLayoutManager(this@ReliefTechiniquesActivity)
            adapter = ProgressSensationAdapter(lista) { item ->
                val intent = Intent(
                    this@ReliefTechiniquesActivity,
                    ProgressSensationDatailActivity::class.java
                )
                intent.putExtra(EXTRA_EXERCISE, item.nome)
                startActivity(intent)
            }
        }

        binding.cvGuidedStretches.setOnClickListener {
            val intent = Intent(this, ExerciseListActivity::class.java)
            intent.putExtra(EXTRA_TECHINIQUE_TYPE_ID, TechiniqueType.GuidedStretching)
            startActivity(intent)
        }

        binding.cvBreathingTechniques.setOnClickListener {
            val intent = Intent(this, ExerciseListActivity::class.java)
            intent.putExtra(EXTRA_TECHINIQUE_TYPE_ID, TechiniqueType.BreathingTechnique)
            startActivity(intent)
        }
    }
}

data class RegistroDor(
    val nome: String,
    val vezes: Int,
    val ultimaVez: String
)