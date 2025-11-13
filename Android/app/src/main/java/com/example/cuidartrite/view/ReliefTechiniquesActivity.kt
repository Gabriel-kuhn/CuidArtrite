package com.example.cuidartrite.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.databinding.ActivityReliefTechiniquesBinding

class ReliefTechiniquesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReliefTechiniquesBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReliefTechiniquesBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        binding.cvDailyProgress.setOnClickListener {
            startActivity(Intent(this, ProgressAndSensationDiaryActivity::class.java))
        }
    }
}
