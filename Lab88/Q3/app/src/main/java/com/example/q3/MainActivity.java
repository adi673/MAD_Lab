package com.example.q3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMovies;
    Button btnAdd;
    DatabaseHelper db;

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
        lvMovies = findViewById(R.id.lvMovies);
        btnAdd = findViewById(R.id.btnAdd);
        db = new DatabaseHelper(this);

        loadMovies();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddReviewActivity.class);
            startActivity(intent);
        });

        lvMovies.setOnItemClickListener((parent, view, position, id) -> {
            String movieName = (String) lvMovies.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("MOVIE_NAME", movieName);
            startActivity(intent);
        });
    }
    private void loadMovies() {
        ArrayList<String> movies = db.getAllMovies();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        lvMovies.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
    }


}