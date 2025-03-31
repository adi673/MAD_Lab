package com.example.q2;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imgDisplay;
    private Button btnMenu;
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
        imgDisplay = findViewById(R.id.imgDisplay);
        btnMenu = findViewById(R.id.btnMenu);

        // Set up the button click listener
        btnMenu.setOnClickListener(view -> showPopupMenu(view));
    }
    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_image1:
                    displayImage(R.drawable.ic_search, "Image - 1");
                    return true;

                case R.id.item_image2:
                    displayImage(R.drawable.ic_search, "Image - 2");
                    return true;

                default:
                    return false;
            }
        });

        popup.show();
    }

    // Function to display image and show Toast
    private void displayImage(int imageResId, String message) {
        imgDisplay.setImageResource(imageResId);
        imgDisplay.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Selected: " + message, Toast.LENGTH_SHORT).show();
    }
}