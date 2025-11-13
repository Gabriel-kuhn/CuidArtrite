package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.databinding.ActivityAddPainAssessmentBinding

class AddPainAssessmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPainAssessmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPainAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}