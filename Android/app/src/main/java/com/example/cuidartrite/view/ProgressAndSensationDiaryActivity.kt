package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.databinding.ActivityProgressAndSensationDiaryBinding
import com.example.cuidartrite.view.adapter.ProgressSensationAdapter

class ProgressAndSensationDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressAndSensationDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressAndSensationDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = listOf(
            RegistroDor("Joelho", 3, "10/11/2025"),
            RegistroDor("Ombro", 1, "08/11/2025"),
            RegistroDor("Costas", 5, "12/11/2025")
        )

        binding.recyclerTabela.apply {
            layoutManager = LinearLayoutManager(this@ProgressAndSensationDiaryActivity)
            adapter = ProgressSensationAdapter(lista) { item ->
                val intent = Intent(this@ProgressAndSensationDiaryActivity, ProgressSensationDatailActivity::class.java)
                intent.putExtra(EXTRA_EXERCISE, item.nome)
                startActivity(intent)
            }
        }
    }
}


data class RegistroDor(
    val nome: String,
    val vezes: Int,
    val ultimaVez: String
)