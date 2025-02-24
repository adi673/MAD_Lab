package com.example.q1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spin;
    EditText numebrPlate, RCnumber;
    Button submitButton;
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

        spin=findViewById(R.id.vehicle_Spinner);
        numebrPlate=findViewById(R.id.numberPlate);
        RCnumber=findViewById(R.id.rcNumber);
        submitButton=findViewById(R.id.submitButton);

        String[] vehicleLlist={"Car","bike","etc."};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,vehicleLlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int date=cal.get(Calendar.DATE);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedVehicle = spin.getSelectedItem().toString();
                Intent intent=new Intent(MainActivity.this, secondPage.class);
                intent.putExtra("numberPlate",numebrPlate.getText().toString());
                intent.putExtra("rcPlate",RCnumber.getText().toString());
                intent.putExtra("selectedVehicle", selectedVehicle);
                startActivity(intent);
            }
        });



    }
}