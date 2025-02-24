package com.example.customspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        Spinner spinner = findViewById(R.id.spinner);

        // Sample Data: Text and corresponding images from drawable
        List<SpinnerItem> items = new ArrayList<>();
        items.add(new SpinnerItem("Apple", R.drawable.apple));
        items.add(new SpinnerItem("Banana", R.drawable.banana));
        items.add(new SpinnerItem("Cherry", R.drawable.cherry));

        // Set Custom Adapter
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, items);
        spinner.setAdapter(adapter);

        // Handle Item Selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem selectedItem = (SpinnerItem) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Selected: " + selectedItem.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
            }
        });

    }
}