package com.example.lab4;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class secondClass extends AppCompatActivity {
    TextView textView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView= findViewById(R.id.textView);
        String receivedMessage= getIntent().getStringExtra("message");

        if (receivedMessage != null) {
            textView.setText(receivedMessage);
        } else {
            textView.setText("No message received");
        }
    }
}
