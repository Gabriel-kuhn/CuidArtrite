package com.example.cuidartrite.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuidartrite.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnPainAssessment.setOnClickListener(v -> {
            Intent intent = new Intent(this, PainAssessmentActivity.class);
            startActivity(intent);
        });

        binding.btnReliefTechniques.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReliefTechiniquesActivity.class);
            startActivity(intent);
        });

//        binding.btnAgenda.setOnClickListener(v -> {
//            Intent intent = new Intent(this, asd.class);
//            startActivity(intent);
//        });

        binding.btnEducationalContent.setOnClickListener(v -> {
            Intent intent = new Intent(this, EducationalContentActivity.class);
            startActivity(intent);
        });
    }
}
