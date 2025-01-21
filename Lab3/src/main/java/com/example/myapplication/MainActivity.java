package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private StringBuilder currentInput = new StringBuilder();
    private StringBuilder textInputShower= new StringBuilder();
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";
    private boolean operatorPressed = false;




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
        displayTextView = findViewById(R.id.displayTextView);
        int[] numberButtonIds={R.id.but0,R.id.but1,R.id.but2,R.id.but3,R.id.but4,R.id.but5,R.id.but6,R.id.but7,R.id.but8,R.id.but9};
        int[] operatorButtonIds={R.id. butPercentage,R.id. butPercentage,R.id.butDivide,R.id.butMulti,R.id.butSub,R.id.butAdd,R.id.butEqual};

        View.OnClickListener numberClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button= (Button) v;
                currentInput.append(button.getText().toString());
                textInputShower.append((button.getText().toString()));
                displayTextView.setText(textInputShower.toString());
            }
        };
        for(int id: numberButtonIds){
            findViewById(id).setOnClickListener(numberClickListener);
        }

        View.OnClickListener operatorClickListner= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentInput.toString().isEmpty() && !operatorPressed){
                    num1 = Double.parseDouble(currentInput.toString());
                    operator = ((Button) v).getText().toString();
                    textInputShower.append((((Button) v).getText().toString()));
                    displayTextView.setText(textInputShower.toString());
                    operatorPressed = true;
                    currentInput.setLength(0); // Clear the current input
                }
            }
        };
        for(int id: operatorButtonIds){
            findViewById(id).setOnClickListener(operatorClickListner);
        }

        findViewById(R.id.butEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputShower.append("=");
                if(!currentInput.toString().isEmpty() && operatorPressed){
                    num2 = Double.parseDouble(currentInput.toString());

                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                displayTextView.setText("Error: Division by 0");
                                return;
                            }
                            break;
                    }
                }
            }
        });
    }
}