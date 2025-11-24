package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_TECHINIQUE_TYPE_ID
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityReliefTechiniquesBinding
import com.example.cuidartrite.enums.TechiniqueType
import com.example.cuidartrite.network.api.controller.ApiTecnicaController
import com.example.cuidartrite.network.models.User
import com.example.cuidartrite.view.adapter.ProgressSensationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReliefTechiniquesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReliefTechiniquesBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReliefTechiniquesBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        val user: User = intent.extras!!.getParcelable(EXTRA_USER)!!

        lifecycleScope.launch(Dispatchers.IO) {

            Log.d("Relieft Techiniques Actiivty", "userId: ${user.id}")
            val response = ApiTecnicaController().listarTecnicasResumidas(user.id!!)
            Log.d("Relieft Techiniques Actiivty", response.toString())

            withContext(Dispatchers.Main) {
                if (response.isNullOrEmpty()) {
                    binding.tvHistoricoVazio.visibility = VISIBLE
                }

                binding.recyclerTabela.apply {
                    layoutManager = LinearLayoutManager(this@ReliefTechiniquesActivity)
                    adapter = ProgressSensationAdapter(response) { item ->
                        val intent = Intent(
                            this@ReliefTechiniquesActivity,
                            ProgressSensationDetailActivity::class.java
                        )
                        intent.putExtra(EXTRA_USER, user)
                        intent.putExtra(EXTRA_EXERCISE, item)
                        startActivity(intent)
                    }
                }
            }
        }


        binding.cvGuidedStretches.setOnClickListener {
            val intent = Intent(this, ExerciseListActivity::class.java)
            intent.putExtra(EXTRA_TECHINIQUE_TYPE_ID, TechiniqueType.GuidedStretching)
            intent.putExtra(EXTRA_USER, user)
            startActivity(intent)
        }

        binding.cvBreathingTechniques.setOnClickListener {
            val intent = Intent(this, ExerciseListActivity::class.java)
            intent.putExtra(EXTRA_TECHINIQUE_TYPE_ID, TechiniqueType.BreathingTechnique)
            intent.putExtra(EXTRA_USER, user)
            startActivity(intent)
        }
    }
}