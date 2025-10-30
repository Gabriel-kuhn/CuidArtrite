package com.example.cuidartrite.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuidartrite.databinding.HomeBinding;

public class Home extends AppCompatActivity {

    private HomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
        binding.btnEducationalContent.setOnClickListener(v -> {
            Intent intent = new Intent(this, EducationalContentActivity.class);
            startActivity(intent);
        });
    }
}
