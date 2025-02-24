package com.example.q1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class secondPage extends AppCompatActivity {
    TextView numberPlate;
    TextView RcPlate;
    TextView showVehicle;
    Button confirm;
    StringBuilder id=new StringBuilder("");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        numberPlate=findViewById(R.id.showNumber);
        RcPlate=findViewById(R.id.showRc);
        confirm=findViewById(R.id.confirm);
        showVehicle=findViewById(R.id.showVehicle);
        String number= getIntent().getStringExtra("numberPlate");

        String rc=getIntent().getStringExtra("rcPlate");
        String Vehicle=getIntent().getStringExtra("selectedVehicle");
        numberPlate.setText(number.toString());
        RcPlate.setText(rc.toString());
        showVehicle.setText(Vehicle);
        id.append(number);
        id.append(rc);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(secondPage.this, id.toString(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
