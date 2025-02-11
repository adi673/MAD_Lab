package com.example.lab4_q4;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class OrderSummaryActivity extends AppCompatActivity {

    private TextView orderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderSummary = findViewById(R.id.orderSummary);

        // Get data from intent
        ArrayList<String> orderedItems = getIntent().getStringArrayListExtra("orderedItems");
        int totalCost = getIntent().getIntExtra("totalCost", 0);

        // Display order details
        StringBuilder summary = new StringBuilder("You ordered:\n");
        for (String item : orderedItems) {
            summary.append("• ").append(item).append("\n");
        }
        summary.append("\nTotal Cost: ₹").append(totalCost);

        orderSummary.setText(summary.toString());
    }
}