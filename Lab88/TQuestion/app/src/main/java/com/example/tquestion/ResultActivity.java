package com.example.tquestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView totalExpensesTv, differenceTv;
    private Button backButton;   // Back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        totalExpensesTv = findViewById(R.id.tv_total_expenses);
        differenceTv = findViewById(R.id.tv_difference);
        backButton = findViewById(R.id.btn_back);  // Initialize back button

        // Retrieve data from Intent
        double totalExpenses = getIntent().getDoubleExtra("TOTAL_EXPENSES", 0.0);
        double difference = getIntent().getDoubleExtra("DIFFERENCE", 0.0);

        // Display the results
        totalExpensesTv.setText("Total Expenses: ₹" + totalExpenses);
        differenceTv.setText("Balance Left: ₹" + difference);

        // Back button logic to return to MainActivity (TabLayout)
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}