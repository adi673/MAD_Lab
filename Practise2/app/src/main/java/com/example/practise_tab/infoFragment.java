package com.example.practise_tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class infoFragment extends Fragment {
    public TextView textViewName;
    public TextView textViewLast;
    public Button Submit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_fragment, container, false);
        textViewName=view.findViewById(R.id.editText);
        textViewLast=view.findViewById(R.id.editText2);
        Submit=view.findViewById(R.id.click);

        Submit.setOnClickListener(v->{
            String name=textViewName.getText().toString();
            String LastName=textViewLast.getText().toString();


            Intent intent= new Intent(getActivity(), MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("lastName",LastName);
            startActivity(intent);


        });

        return view;
    }
}
