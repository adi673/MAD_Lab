package com.example.q3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddReviewActivity extends AppCompatActivity {
    EditText etName, etYear, etRating;
    Button btnSave, btnBack;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etRating = findViewById(R.id.etRating);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String yearStr = etYear.getText().toString().trim();
            String ratingStr = etRating.getText().toString().trim();

            if (name.isEmpty() || yearStr.isEmpty() || ratingStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = Integer.parseInt(yearStr);
            int rating = Integer.parseInt(ratingStr);

            if (rating < 1 || rating > 5) {
                Toast.makeText(this, "Rating must be between 1-5", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addReview(name, year, rating);
            Toast.makeText(this, "Review Added", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
