package com.example.q4_url;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText urlInput;
    private Button buttonOK, buttonCancel;
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
        // Initialize UI elements
        urlInput = findViewById(R.id.urlInput);
        buttonOK = findViewById(R.id.buttonOK);
        buttonCancel = findViewById(R.id.buttonCancel);

        // OK Button Click - Open URL in browser
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = urlInput.getText().toString().trim();

                if (!urlText.isEmpty()) {
                    // Ensure the URL starts with "http://" or "https://"
                    if (!urlText.startsWith("http://") && !urlText.startsWith("https://")) {
                        urlText = "http://" + urlText;
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlText));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancel Button Click - Clear Input
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlInput.setText("");  // Clear the input field
            }
        });
    }
}
