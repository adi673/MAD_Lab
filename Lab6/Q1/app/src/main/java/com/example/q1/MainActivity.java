package com.example.q1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvTitle,tvContent;
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
        toolbar=findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);
        tvTitle=findViewById(R.id.tvTitle);
        tvContent=findViewById(R.id.tvContent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.menu_workout){
            tvTitle.setText("Workout Plans");
            String content = "Workout Plans:\n" +
                    "- Weight Loss\n" +
                    "- Cardio\n" +
                    "- Strength Training\n" +
                    "- Yoga & Flexibility";
            tvContent.setText(content);
            Toast.makeText(this, "Workout Plans", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.menu_trainers){
            tvTitle.setText("Trainers");
            String content = "Trainers:\n" +
                    "1. John Doe - Cardio Specialist\n" +
                    "2. Emma Smith - Weight Loss Expert\n" +
                    "3. Mark Lee - Strength Coach";
            tvContent.setText(content);
            Toast.makeText(this, "Trainers", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.menu_membership){
            tvTitle.setText("Membership");
            String content = "Membership Packages:\n" +
                    "- Basic: $30/month\n" +
                    "- Premium: $50/month\n" +
                    "- Annual: $500/year";
            tvContent.setText(content);
            Toast.makeText(this, "Membership", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
}