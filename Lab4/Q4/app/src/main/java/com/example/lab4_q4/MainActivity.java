package com.example.lab4_q4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.inappmessaging.model.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private CheckBox item1, item2, item3, item4, item5;
    private View submitButton;
    private HashMap<CheckBox, Integer> itemPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        submitButton = findViewById(R.id.submitButton); // ✅ Correctly initializing the button

        // Store item prices
        itemPrices = new HashMap<>();
        itemPrices.put(item1, 120);
        itemPrices.put(item2, 200);
        itemPrices.put(item3, 150);
        itemPrices.put(item4, 80);
        itemPrices.put(item5, 50);

        // ✅ Fixed: Set onClickListener properly
        submitButton.setOnClickListener(v -> {
            ArrayList<String> orderedItems = new ArrayList<>();
            int totalCost = 0;

            // Check selected items and calculate total cost
            for (CheckBox checkBox : itemPrices.keySet()) {
                if (checkBox.isChecked()) {
                    orderedItems.add(checkBox.getText().toString());
                    totalCost += itemPrices.get(checkBox);
                }
            }

            if (orderedItems.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please select at least one item!", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Fixed: Disable checkboxes after order submission
            for (CheckBox checkBox : itemPrices.keySet()) {
                checkBox.setEnabled(false);
            }

            // ✅ Fixed: Disable submit button (instead of `equals(false)`)
            submitButton.setEnabled(false);

            // Start new activity and pass data
            Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
            intent.putStringArrayListExtra("orderedItems", orderedItems);
            intent.putExtra("totalCost", totalCost);
            startActivity(intent);
        });
    }
}
