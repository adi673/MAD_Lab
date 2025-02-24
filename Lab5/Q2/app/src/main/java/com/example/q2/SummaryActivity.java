package com.example.q2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    TextView summaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryText = findViewById(R.id.summaryText);

        // Retrieve passed data
        String source = getIntent().getStringExtra("source");
        String destination = getIntent().getStringExtra("destination");
        String date = getIntent().getStringExtra("date");
        String tripType = getIntent().getStringExtra("tripType");

        // Display details
        String summary = "Source: " + source + "\n"
                + "Destination: " + destination + "\n"
                + "Travel Date: " + date + "\n"
                + "Trip Type: " + tripType;

        summaryText.setText(summary);
    }
}
