package com.example.lab4_q3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private ImageView modeImageView;
    private Button changeModeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toggleButton = findViewById(R.id.toggleButton);
        modeImageView = findViewById(R.id.modeImageView);
        changeModeButton = findViewById(R.id.changeModeButton);

        // Set default state
        updateUI(toggleButton.isChecked());

        // ToggleButton Change Listener
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateUI(isChecked));

        // Change Mode Button Click
        changeModeButton.setOnClickListener(v -> {
            boolean newState = !toggleButton.isChecked();
            toggleButton.setChecked(newState);
            updateUI(newState);
        });
    }

    // Function to update UI based on mode
    private void updateUI(boolean isWiFiMode) {
        if (isWiFiMode) {
            modeImageView.setImageResource(R.drawable.wifi_icon);
            Toast.makeText(this, "Wi-Fi Mode Enabled", Toast.LENGTH_SHORT).show();
        } else {
            modeImageView.setImageResource(R.drawable.mobile_data_icon);
            Toast.makeText(this, "Mobile Data Mode Enabled", Toast.LENGTH_SHORT).show();
        }
    }

}