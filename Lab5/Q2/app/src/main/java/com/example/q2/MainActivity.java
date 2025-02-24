package com.example.q2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Spinner sourceSpinner, destinationSpinner;
    TextView selectedDate;
    ToggleButton tripToggle;
    Button submitButton, resetButton;
    Calendar calendar;
    int year, month, day;
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


        sourceSpinner = findViewById(R.id.sourceSpinner);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        selectedDate = findViewById(R.id.selectedDate);
        tripToggle = findViewById(R.id.tripToggle);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        // Set up source and destination spinners
        String[] locations = {"New York", "Los Angeles", "Chicago", "Houston", "Miami"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        // Initialize date picker
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        updateDateDisplay();

        selectedDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        updateDateDisplay();
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Submit button action
        submitButton.setOnClickListener(view -> {
            String source = sourceSpinner.getSelectedItem().toString();
            String destination = destinationSpinner.getSelectedItem().toString();
            String date = selectedDate.getText().toString();
            String tripType = tripToggle.isChecked() ? "Round Trip" : "One Way";

            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("source", source);
            intent.putExtra("destination", destination);
            intent.putExtra("date", date);
            intent.putExtra("tripType", tripType);
            startActivity(intent);
        });

        // Reset button action
        resetButton.setOnClickListener(view -> {
            sourceSpinner.setSelection(0);
            destinationSpinner.setSelection(0);
            tripToggle.setChecked(false);
            calendar = Calendar.getInstance();
            updateDateDisplay();
        });


    }
    private void updateDateDisplay() {
        selectedDate.setText(day + "/" + (month + 1) + "/" + year);
    }
}