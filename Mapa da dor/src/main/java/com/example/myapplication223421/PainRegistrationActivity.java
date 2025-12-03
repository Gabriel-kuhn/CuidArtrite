package com.example.myapplication223421;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class PainRegistrationActivity extends AppCompatActivity {

    private Set<ImageView> selectedJoints = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_registration);

        // Lista de articulações
        ImageView[] joints = {
                findViewById(R.id.jointShoulderLeft),
                findViewById(R.id.jointShoulderRight),
                findViewById(R.id.jointElbowLeft),
                findViewById(R.id.jointElbowRight),
                findViewById(R.id.jointWristLeft),
                findViewById(R.id.jointWristRight),
                findViewById(R.id.jointHipLeft),
                findViewById(R.id.jointHipRight),
                findViewById(R.id.jointKneeLeft),
                findViewById(R.id.jointKneeRight),
                findViewById(R.id.jointAnkleLeft),
                findViewById(R.id.jointAnkleRight),
        };

        TextView jointLabel = findViewById(R.id.jointLabel);

        for (ImageView joint : joints) {
            joint.setOnClickListener(v -> {
                if (selectedJoints.contains(joint)) {
                    // Já está selecionada → desmarcar
                    joint.setColorFilter(null);
                    selectedJoints.remove(joint);
                } else {
                    // Selecionar
                    joint.setColorFilter(0xFF00FF00); // verde
                    selectedJoints.add(joint);
                }

                // Atualiza o texto
                if (selectedJoints.isEmpty()) {
                    jointLabel.setText("Selecione uma articulação");
                } else {
                    StringBuilder sb = new StringBuilder("Selecionado: ");
                    for (ImageView j : selectedJoints) {
                        sb.append(j.getTag()).append(", ");
                    }
                    jointLabel.setText(sb.substring(0, sb.length() - 2));
                }
            });
        }

        // SeekBar e botão permanecem iguais
        SeekBar seek = findViewById(R.id.seekPain);
        TextView txtValue = findViewById(R.id.txtPainValue);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtValue.setText(String.valueOf(i));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            if (selectedJoints.isEmpty()) {
                Toast.makeText(this, "Selecione pelo menos uma articulação!", Toast.LENGTH_SHORT).show();
                return;
            }

            int painValue = seek.getProgress();
            StringBuilder sb = new StringBuilder();
            for (ImageView j : selectedJoints) sb.append(j.getTag()).append(", ");

            Toast.makeText(this,
                    "Registrado:\nArticulações: " + sb.substring(0, sb.length() - 2) + "\nDor: " + painValue,
                    Toast.LENGTH_LONG).show();
        });
    }
}