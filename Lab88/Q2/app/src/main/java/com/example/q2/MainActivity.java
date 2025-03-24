package com.example.q2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ItemName,ItemCost;
    Button btnaddItem,btnCalculateTotal;
    Spinner spGroceryItems;
    TextView tvTotalCost;

    DatabaseHelper db;
    ArrayList<String> selectedItems;
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
        ItemName=findViewById(R.id.ItemName);
        ItemCost=findViewById(R.id.ItemCost);
        btnaddItem=findViewById(R.id.btnAddItem);
        btnCalculateTotal=findViewById(R.id.calculatTotal);
        spGroceryItems=findViewById(R.id.GroceryItems);
        tvTotalCost=findViewById(R.id.totalCost);

        db=new DatabaseHelper(this);
        selectedItems=new ArrayList<>();

        loadSpinnerData();
        btnaddItem.setOnClickListener(v->{
            String itemNameStr=ItemName.getText().toString().trim();
            String itemCostStr=ItemCost.getText().toString().trim();

            if(itemCostStr.isEmpty() || itemNameStr.isEmpty()){
                Toast.makeText(this, "Enter Name and Cost", Toast.LENGTH_SHORT).show();
                return;
            }

            double itemCost=Double.parseDouble(itemCostStr);
            db.addGroceryItem(itemNameStr,itemCost);

            Toast.makeText(this, "Item Added Succesfully", Toast.LENGTH_SHORT).show();
            ItemName.setText("");
            ItemCost.setText("");

            loadSpinnerData();
        });

        // In onCreate()
        spGroceryItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = spGroceryItems.getSelectedItem().toString();

                // ✅ FIX: Remove duplicates immediately when selecting
                if (!selectedItems.contains(selectedItem)) {
                    selectedItems.add(selectedItem);
                    Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Already selected ", Toast.LENGTH_SHORT).show();
                }

                // ✅ FIX: Update total immediately after selection
//                double totalCost = db.getTotalCost(selectedItems);
//                tvTotalCost.setText("Total Cost is: ₹" + totalCost);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnCalculateTotal.setOnClickListener(v->{
            double totalCost = db.getTotalCost(selectedItems);
            tvTotalCost.setText("Total Cost is: ₹" + totalCost);
        });

    }

    private void loadSpinnerData(){
        ArrayList<String> items=db.getAllGroceryItems();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGroceryItems.setAdapter(adapter);
    }
}