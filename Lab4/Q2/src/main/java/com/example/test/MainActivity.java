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

        Button buttonCupcake = findViewById(R.id.buttonCupcake);
        buttonCupcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("Cupcake (1.5)", R.drawable.icon_cupcake);
                showToast("Cupcake (1.5)", "icon_cupcake");
            }
        });

        Button buttonFroYo = findViewById(R.id.buttonFroYo);
        buttonFroYo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("FroYo (2.2)"," icon_froyo");
            }
        });

        Button buttonIceCreamSandwich = findViewById(R.id.buttonIceCreamSandwich);
        buttonIceCreamSandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Ice Cream Sandwich (4.0)", "icon_ice_cream_sandwich");
            }
        });

        Button buttonNougat = findViewById(R.id.buttonNougat);
        buttonNougat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Nougat (7.0)", "icon_nougat");
            }
        });

        Button buttonAndroidTen = findViewById(R.id.buttonAndroidTen);
        buttonAndroidTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Android 10", "icon_android_ten");
            }
        });

    }

    private void showToast(String versionName, String imageResource) {
        // Create a custom Toast layout with an image and message
        Toast toast = Toast.makeText(MainActivity.this, versionName, Toast.LENGTH_SHORT);
//        toast.getView().setBackgroundResource(imageResource);
        toast.setText(imageResource);
        toast.show();
    }
}