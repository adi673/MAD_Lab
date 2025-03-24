package com.example.exammidsem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class expenses extends Fragment {
    EditText food,clothes,travel;
    Button sum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.expenses_tab, container, false);
        food=view.findViewById(R.id.foodExpenses);
        clothes=view.findViewById(R.id.clothesExpenses);
        travel=view.findViewById(R.id.travelExpenses);
        sum=view.findViewById(R.id.sum);
        int traelExpenses=1000;
        int clothExpenses=500;
        int  foodExpenses=1000;
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent2=new Intent(this);
//                intent2.putExtra("travel",traelExpenses);
//                intent2.putExtra("cloth",clothExpenses);
//                intent2.putExtra("food",foodExpenses);
//                intent2.putExtra("totalExp",2500);
//                startActivity(intent2);
                Toast.makeText(getActivity(), "Total Expenses are "+"2500", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
