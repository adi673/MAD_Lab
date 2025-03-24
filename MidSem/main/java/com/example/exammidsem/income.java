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

public class income extends Fragment {
   Button move;
   EditText income;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.income_tab, container,false);
        move=view.findViewById(R.id.move);
        income=view.findViewById(R.id.totalIncome);

        String val= income.getText().toString();
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "income is : "+5000, Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getActivity(),secondclass.class);
                intent1.putExtra("Income","5000");
                startActivity(intent1);
            }
        });




        return view;
    }


}
