package com.example.q3;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView tvName, tvYear, tvRating;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvName = findViewById(R.id.tvName);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);

        db = new DatabaseHelper(this);

        String movieName = getIntent().getStringExtra("MOVIE_NAME");
        Cursor cursor = db.getMovieDetails(movieName);

        if (cursor.moveToFirst()) {
            tvName.setText(cursor.getString(1));
            tvYear.setText("Year: " + cursor.getInt(2));
            tvRating.setText("Rating: " + cursor.getInt(3) + "/5");
        }

        cursor.close();
    }
}