package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;
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
        listView = findViewById(R.id.listViewTasks);
        Button btnAddTask = findViewById(R.id.btnAddTask);

        db = new DatabaseHelper(this);
        loadTasks();

        // Open Add/Edit Task Activity
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });

        // Edit or Delete task on item click
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTask = taskList.get(position);
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            intent.putExtra("task", selectedTask);
            Toast.makeText(this, selectedTask, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }
    private void loadTasks() {
        taskList = db.getAllTasks();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }
}