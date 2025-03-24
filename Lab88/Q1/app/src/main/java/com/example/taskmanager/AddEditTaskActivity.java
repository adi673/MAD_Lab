package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {
    EditText etTaskName, etDueDate;
    Spinner spPriority;
    Button btnSave, btnDelete;
    DatabaseHelper db;
    int taskId = -1;   // To track the ID when editing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        etTaskName = findViewById(R.id.etTaskName);
        etDueDate = findViewById(R.id.etDueDate);
        spPriority = findViewById(R.id.spPriority);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        db = new DatabaseHelper(this);

        // 🌟 Set Spinner items
        String[] priorities = {"Low", "Medium", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priorities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPriority.setAdapter(adapter);

        // 🌟 Check if we are editing an existing task
        Intent intent = getIntent();
        if (intent.hasExtra("task")) {
            String oldTask = intent.getStringExtra("task");

            // Split the task into parts: ID, name, date, priority
            String[] taskParts = oldTask.split("\n");

            // Retrieve and parse the task ID
            taskId = Integer.parseInt(taskParts[0]);  // First line is the ID
            etTaskName.setText(taskParts[1]);
            etDueDate.setText(taskParts[2]);

            if (taskParts.length > 3) {
                int spinnerPosition = adapter.getPosition(taskParts[3]);
                spPriority.setSelection(spinnerPosition);
            }

            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);  // Hide delete button for new task
        }

        // ✅ Save button click listener (works for both add and edit)
        btnSave.setOnClickListener(v -> {
            String taskName = etTaskName.getText().toString();
            String dueDate = etDueDate.getText().toString();
            String priority = spPriority.getSelectedItem().toString();

            if (taskId == -1) {
                // Adding a new task
                db.addTask(taskName, dueDate, priority);
                Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Editing an existing task
                db.updateTask(taskId, taskName, dueDate, priority);
                Toast.makeText(this, "Task updated successfully", Toast.LENGTH_SHORT).show();
            }

            finish();
        });

        // ✅ Delete button click listener
        btnDelete.setOnClickListener(v -> {
            if (taskId != -1) {
                db.deleteTask(taskId);    // Delete task by ID
                Toast.makeText(this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
