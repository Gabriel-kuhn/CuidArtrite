package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityProgressAndSensationDiaryBinding
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose

class ProgressSensationDetailActivity : AppCompatActivity() {

    private lateinit var exercise: TecnicaResumidaDetalheRespose
    private lateinit var binding: ActivityProgressAndSensationDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressAndSensationDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exercise = intent.extras!!.getParcelable(EXTRA_EXERCISE)!!

        binding.tvTitle.text = exercise.titulo
    }
}