package com.example.exam;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class SecondTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Load the second tab layout
        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);

        // Show toast with image
        showCustomToast(getContext());

        return view;
    }
    Bundle bundle = getArguments();
    String name = bundle.getString("NAME");
    String regNumber = bundle.getString("REG_NUMBER");

    private void showCustomToast(Context context) {
        if (context == null) return; // Check for null to prevent crashes

        // Inflate custom toast layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.custom_toast, null);

        // Set the message text in the toast
        TextView toastText = toastView.findViewById(R.id.toast_text);
        toastText.setText("Welcome, " + name + "! Your Reg: " + regNumber);

        // Set the image in the toast
        ImageView toastImage = toastView.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.success_icon); // Change to your actual image



        // Create and display the custom toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0); // Position at center
        toast.show();
    }
}

//public class SecondTabFragment extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Load the second tab layout
//        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);
//
//        // Show toast message when fragment is opened
//        showToastWithImage(getContext());
//
//        return view;
//    }
//
//    private void showToastWithImage(Context context) {
//        // Create a toast message
//        Toast toast = Toast.makeText(context, "Welcome to the Second Tab!", Toast.LENGTH_LONG);
//
//        // Add an image to the toast message
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(R.drawable.ic_launcher_foreground);  // Replace with your actual image
//
//        // Display the toast
//        toast.show();
//    }
//}
