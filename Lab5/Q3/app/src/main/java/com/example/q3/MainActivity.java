package com.example.q3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Spinner movieName;
    Spinner theatreName;
    TextView selectedDate;
    TextView selectedTime;
    ToggleButton ticket;
    Button submit;
    Button reset;
    Calendar calendar;
    int year;
    int month;
    int date;
    int hr;
    int min;
    int AP;
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
        movieName = findViewById(R.id.movieName);
        theatreName = findViewById(R.id.theatreName);
        selectedDate = findViewById(R.id.selectedDate);
        selectedTime = findViewById(R.id.selectedTime);
        ticket = findViewById(R.id.typeTicket);
        submit = findViewById(R.id.ButtonBook);
        reset = findViewById(R.id.ButtonReset);

        String[] movies = {"Movie1", "Movie2", "Movie3", "Movie4"};
        String[] theatres = {"T1", "T2", "T3", "T4", "T5"};

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movies);
        ArrayAdapter<String> adapterTheatre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theatres);

        movieName.setAdapter(adapterMovie);
        theatreName.setAdapter(adapterTheatre);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        hr = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        updateDateDisplay();
        updateSubmitButtonState();

        // Date Picker
        selectedDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        updateDateDisplay();
                    }, year, month, date);
            datePickerDialog.show();
        });

        // Time Picker
        selectedTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute) -> {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
//                        StringBuilder str=new StringBuilder();
//                        StringBuilder str=new StringBuilder();
//                        str.append(hourOfDay);
//                        str.append(":");
//                        str.append(minute);
                        selectedTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        updateSubmitButtonState();
                    }, hr, min, false);
            timePickerDialog.show();
        });

        // Toggle button for ticket type
        ticket.setOnClickListener(view -> updateSubmitButtonState());

        // Book Now button
        submit.setOnClickListener(view -> {
            String movie = movieName.getSelectedItem().toString();
            String theatre = theatreName.getSelectedItem().toString();
            String date = selectedDate.getText().toString();
            String time = selectedTime.getText().toString();
            String ticketType = ticket.isChecked() ? "Premium" : "Standard";

            // Navigate to booking details page
            Intent intent = new Intent(MainActivity.this, BookingDetailsActivity.class);
            intent.putExtra("movie", movie);
            intent.putExtra("theatre", theatre);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            intent.putExtra("ticketType", ticketType);
            startActivity(intent);
        });

        // Reset button
        reset.setOnClickListener(view -> {
            movieName.setSelection(0);
            theatreName.setSelection(0);
            calendar = Calendar.getInstance();
            updateDateDisplay();
            selectedTime.setText("Select Time");
            ticket.setChecked(false);
            updateSubmitButtonState();
        });
    }
    private void updateDateDisplay() {
        selectedDate.setText(date + "/" + (month + 1) + "/" + year);
    }

    private void updateSubmitButtonState() {
        boolean isPremium = ticket.isChecked();
        boolean isAfterNoon = calendar.get(Calendar.HOUR_OF_DAY) >= 12;

        if (isPremium && !isAfterNoon) {
            submit.setEnabled(false);
        } else {
            submit.setEnabled(true);
        }
    }
}