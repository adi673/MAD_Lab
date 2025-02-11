package com.example.exam;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.exam.R;
public class FirstTabFragment extends Fragment {

    private EditText nameInput, regNumberInput;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_tab, container, false);

        nameInput = view.findViewById(R.id.nameInput);
        regNumberInput = view.findViewById(R.id.regNumberInput);
        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String regNumber = regNumberInput.getText().toString().trim();

            if (name.isEmpty() || regNumber.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass data using Intent
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("NAME", name);
            intent.putExtra("REG_NUMBER", regNumber);
            intent.putExtra("TAB_INDEX", 1); // Move to second tab
            startActivity(intent);
        });

        return view;
    }
}
