package com.example.cuidartrite.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuidartrite.databinding.ActivityReliefTechiniquesBinding;

public class ReliefTechiniquesActivity extends AppCompatActivity {

    private ActivityReliefTechiniquesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReliefTechiniquesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
