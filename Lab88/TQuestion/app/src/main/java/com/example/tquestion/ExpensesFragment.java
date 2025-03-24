package com.example.tquestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExpensesFragment extends Fragment {

    private LinearLayout expensesContainer;
    private Button addExpenseBtn, showTotalBtn;
    private double currentBalance = 10000.00;  // Example balance
    private double totalExpenses = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        expensesContainer = view.findViewById(R.id.expensesContainer);
        addExpenseBtn = view.findViewById(R.id.btn_add_expense);
        showTotalBtn = view.findViewById(R.id.btn_show_total);

        // Button to add multiple expense fields dynamically
        addExpenseBtn.setOnClickListener(v -> addExpenseField());

        // Button to calculate total expenses and move to ResultActivity
        showTotalBtn.setOnClickListener(v -> {
            totalExpenses = calculateTotalExpenses();

            if (totalExpenses == -1) {
                Toast.makeText(getActivity(), "Please enter valid expenses", Toast.LENGTH_SHORT).show();
                return;
            }

            double difference = currentBalance - totalExpenses;

            // Move to ResultActivity using Intent
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("TOTAL_EXPENSES", totalExpenses);
            intent.putExtra("DIFFERENCE", difference);
            startActivity(intent);
        });

        return view;
    }

    // Dynamically add new EditText for expenses
    private void addExpenseField() {
        EditText expenseField = new EditText(getActivity());
        expenseField.setHint("Enter expense");
        expenseField.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        expensesContainer.addView(expenseField);
    }

    // Calculate total expenses
    private double calculateTotalExpenses() {
        double total = 0.0;

        for (int i = 0; i < expensesContainer.getChildCount(); i++) {
            View view = expensesContainer.getChildAt(i);
            if (view instanceof EditText) {
                String expenseStr = ((EditText) view).getText().toString().trim();

                if (!expenseStr.isEmpty()) {
                    try {
                        double expense = Double.parseDouble(expenseStr);
                        total += expense;
                    } catch (NumberFormatException e) {
                        return -1;  // Invalid input
                    }
                }
            }
        }

        return total;
    }
}
