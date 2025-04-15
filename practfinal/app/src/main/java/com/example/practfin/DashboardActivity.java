package com.example.practfin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {
    Button submit;
    Spinner srcSpinner,destSpinner;
    TextView selectedTime,selectedDate;
    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_pref";
    int year;
    int month;
    int date;
    int hr;
    int min;
    int AP;
    Calendar calendar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        int uId = sharedPreferences.getInt("userId", -1);
        String uName = sharedPreferences.getString("userName", "Guest");
        submit = findViewById(R.id.submit);
        srcSpinner = findViewById(R.id.srcSpinner);
        destSpinner = findViewById(R.id.destSpinner);
        selectedDate = findViewById(R.id.selectedDate);
        selectedTime = findViewById(R.id.selectedTime);
        submit.setText(uId+" "+uName);
        String[] src = {"Select Source", "Pune", "Mumbai", "Delhi", "Shimla"};
        String[] dest = {"Select Destination", "Ahemdabad", "Cochin", "Hydrabad", "Jammu"};

        ArrayAdapter<String> srcAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, src);
        srcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srcSpinner.setAdapter(srcAdapter);
        srcSpinner.setSelection(0); // Default to "Select Source"

        ArrayAdapter<String> destAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dest);
        destAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destSpinner.setAdapter(destAdapter);
        destSpinner.setSelection(0); // Default to "Select Destination"

        srcSpinner.setAdapter(srcAdapter);
        destSpinner.setAdapter(destAdapter);
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        date=calendar.get(Calendar.DATE);
        hr=calendar.get(Calendar.HOUR);
        min=calendar.get(Calendar.MINUTE);


// use userId and userName as needed

        selectedDate.setOnClickListener(view -> {
           DatePickerDialog datePickerDialog = new DatePickerDialog(DashboardActivity.this,
                   (view1, year, month, date) -> {
                    calendar.set(year,month,date);
                    updateDateDisplay();
                   },year,month,date);
           datePickerDialog.show();
        });
        selectedTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(DashboardActivity.this,
                    (view1, hourOfDay, minute) -> {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        selectedTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }, hr, min, false);
            timePickerDialog.show();
        });

        submit.setOnClickListener(v -> {
            String selectedSrc = srcSpinner.getSelectedItem().toString();
            String selectedDest = destSpinner.getSelectedItem().toString();

            if (selectedSrc.equals("Select Source") || selectedDest.equals("Select Destination")) {
                Toast.makeText(DashboardActivity.this, "Please select both source and destination", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                srcSpinner.setSelection(0);
                destSpinner.setSelection(0);
                selectedDate.setText("select the Date");
                selectedTime.setText("Select the Time");
            }
        });


    }
    private void updateDateDisplay() {
        selectedDate.setText(date + "/" + (month + 1) + "/" + year);
    }
}
