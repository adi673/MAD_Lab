package com.example.databsepractise;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout io;
    Button add,delete,edit,submit;
    TextView name,email,number, search;
    int id;
    int option;
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

        io=findViewById(R.id.io);
        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        edit=findViewById(R.id.edit);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        search=findViewById(R.id.search);
        submit=findViewById(R.id.submit);
        db = new DatabaseHelper(getApplicationContext());
        io.setVisibility(View.INVISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                io.setVisibility(View.VISIBLE);
                id=1;



            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Search field is empty", Toast.LENGTH_SHORT).show();
                }else{
                    io.setVisibility(View.VISIBLE);
                    id=2;
                    String Semail=search.getText().toString();
                    email.setText(Semail);

                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Search field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    io.setVisibility(View.VISIBLE);
                    String Semail=search.getText().toString();
                    email.setText(Semail);
                    id = 3;
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id==1){
                    String n=name.getText().toString();
                    String e=email.getText().toString();
                    String input = number.getText().toString();
                    int num = input.isEmpty() ? 0 : Integer.parseInt(input);

                    if(n.equals("") || e.equals("") || num==0){
                        Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    }else {

                        int result=db.add(n,e,num);
                        if(result >0){
                            Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else if(id==2){
                    String e=email.getText().toString();
                    if(e.equals("")){
                        Toast.makeText(MainActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
                    }else {
                        int result=db.delete(e);
                        if(result >0){
                            Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else if(id==3){
                    String e=email.getText().toString();
                    if(e.equals("")){
                        Toast.makeText(MainActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
                    }else {
                        String n=name.getText().toString();
                        String input = number.getText().toString();
                        int num = input.isEmpty() ? 0 : Integer.parseInt(input);
                        int result=db.edit(n,e,num);
                        if(result >0){
                            Toast.makeText(MainActivity.this, "Edited Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Failed to edit", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
                }
                id=0;
                io.setVisibility(View.INVISIBLE);
                name.setText("");
                email.setText("");
                number.setText("");
                search.setText("");
            }
        });

    }
}