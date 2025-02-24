package com.example.q3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {
    TextView details;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        details = findViewById(R.id.detailsText);

        String movie = getIntent().getStringExtra("movie");
        String theatre = getIntent().getStringExtra("theatre");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String ticketType = getIntent().getStringExtra("ticketType");

        String confirmation = "Movie: " + movie + "\n" +
                "Theatre: " + theatre + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Ticket Type: " + ticketType + "\n" +
                "Seats Available: 30";

        details.setText(confirmation);
    }
}
