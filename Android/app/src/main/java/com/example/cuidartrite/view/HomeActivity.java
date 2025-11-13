package com.example.cuidartrite.view;

import static com.example.cuidartrite.constants.ConstantsExtra.EXTRA_USER;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuidartrite.databinding.ActivityHomeBinding;
import com.example.cuidartrite.network.models.User;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private ActivityHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getExtras().getParcelable(EXTRA_USER);

        binding.tvUserName.setText(user.getNome());

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
