package com.example.startaditya;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private Button submit;
    private TextView success_or_fail;
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

        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword2);
        submit=(Button) findViewById(R.id.button2);
        success_or_fail=(TextView) findViewById(R.id.textView8);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID=email.getText().toString();
                String pass=password.getText().toString();
                if(emailID.equals("tech@gmail.com") && pass.equals("adi673")){
                    success_or_fail.setText("Logged in Successfully");
                }else{
                    success_or_fail.setText("Invalid Credentials");
                }
            }
        });
    }
}