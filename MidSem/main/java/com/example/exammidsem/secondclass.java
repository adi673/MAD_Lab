package com.example.exammidsem;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class secondclass extends AppCompatActivity {
    EditText incomeshow,expenseshow,totaldiff;
    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

//        String income=getIntent().getStringExtra("Income");
//        String Expenses=getIntent().getStringExtra("totalExp");

        incomeshow.findViewById(R.id.incomeshow);
        expenseshow=findViewById(R.id.Expenseshow);
        totaldiff=findViewById(R.id.totaldiff);

        incomeshow.setText("5000");
        expenseshow.setText("2500");
        String val="2500";
        totaldiff.setText(val);

    }
}
