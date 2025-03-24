package com.example.q5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAge, etEmail;
    private Button btnSave, btnClear;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserData";
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
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load saved data when app is opened
        loadData();

        // Save data when "Save" button is clicked
        btnSave.setOnClickListener(view -> saveData());

        // Clear data when "Clear" button is clicked
        btnClear.setOnClickListener(view -> clearData());
    }

    private void saveData() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String email = etEmail.getText().toString();

        // Save data in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("email", email);
        editor.apply();

        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to Load Saved Data
    private void loadData() {
        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String email = sharedPreferences.getString("email", "");

        etName.setText(name);
        etAge.setText(age);
        etEmail.setText(email);
    }

    // Function to Clear Data
    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        etName.setText("");
        etAge.setText("");
        etEmail.setText("");

        Toast.makeText(this, "Data Cleared!", Toast.LENGTH_SHORT).show();
    }
}