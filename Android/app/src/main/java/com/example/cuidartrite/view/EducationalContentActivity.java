package com.example.cuidartrite.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuidartrite.databinding.ActivityEducationalContentBinding;

public class EducationalContentActivity extends AppCompatActivity {

    private ActivityEducationalContentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEducationalContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.card1.setOnClickListener(v -> {
            if (binding.content1.getVisibility() == VISIBLE) {
                binding.content1.setVisibility(GONE);
            } else {
                binding.content1.setVisibility(VISIBLE);
            }
        });

        binding.card2.setOnClickListener(v -> {
            if (binding.content2.getVisibility() == VISIBLE) {
                binding.content2.setVisibility(GONE);
            } else {
                binding.content2.setVisibility(VISIBLE);
            }
        });

        binding.card3.setOnClickListener(v -> {
            if (binding.content3.getVisibility() == VISIBLE) {
                binding.content3.setVisibility(GONE);
            } else {
                binding.content3.setVisibility(VISIBLE);
            }
        });

        binding.card4.setOnClickListener(v -> {
            if (binding.content4.getVisibility() == VISIBLE) {
                binding.content4.setVisibility(GONE);
            } else {
                binding.content4.setVisibility(VISIBLE);
            }
        });

        binding.card5.setOnClickListener(v -> {
            if (binding.content5.getVisibility() == VISIBLE) {
                binding.content5.setVisibility(GONE);
            } else {
                binding.content5.setVisibility(VISIBLE);
            }
        });

        binding.card6.setOnClickListener(v -> {
            if (binding.content6.getVisibility() == VISIBLE) {
                binding.content6.setVisibility(GONE);
            } else {
                binding.content6.setVisibility(VISIBLE);
            }
        });

        binding.card7.setOnClickListener(v -> {
            if (binding.content7.getVisibility() == VISIBLE) {
                binding.content7.setVisibility(GONE);
            } else {
                binding.content7.setVisibility(VISIBLE);
            }
        });

        binding.card8.setOnClickListener(v -> {
            if (binding.content8.getVisibility() == VISIBLE) {
                binding.content8.setVisibility(GONE);
            } else {
                binding.content8.setVisibility(VISIBLE);
            }
        });

        binding.card9.setOnClickListener(v -> {
            if (binding.content9.getVisibility() == VISIBLE) {
                binding.content9.setVisibility(GONE);
            } else {
                binding.content9.setVisibility(VISIBLE);
            }
        });
    }
}
