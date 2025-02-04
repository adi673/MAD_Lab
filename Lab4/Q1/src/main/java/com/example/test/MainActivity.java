package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button buttonToast;
    private ToggleButton toggleButton;

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

        buttonToast = findViewById(R.id.buttonToast);
        toggleButton = findViewById(R.id.toggleButton);

        // Set the button toast onClickListener
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Button clicked!", R.drawable.image_button); // Replace with actual image resource
            }
        });

        // Set the toggle button toast onClickListener
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showCustomToast("Toggle is ON", R.drawable.image_on); // Replace with actual image resource
            } else {
                showCustomToast("Toggle is OFF", R.drawable.image_off); // Replace with actual image resource
            }
        });


    }

    private void showCustomToast(String message, int imageResId) {
        // Create a custom Toast layout
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);

        // Create a custom layout for the Toast
        RelativeLayout layout = new RelativeLayout(getApplicationContext());
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(imageResId); // Set the image based on the resource ID
        layout.addView(imageView);

        // Set the Toast layout
        toast.setView(layout);

        // Show the Toast
        toast.show();
    }
}