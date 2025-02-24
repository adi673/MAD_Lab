package com.example.practise_tab;

import static java.sql.Types.NULL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class viewFragment extends Fragment {
    public TextView name;
    public TextView Last;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.view_fragment, container, false);
        name = view.findViewById(R.id.name);
        Last= view.findViewById(R.id.lastName);

        if(getActivity() != null){
            String FirstName= getActivity().getIntent().getStringExtra("name");
            String LastName= getActivity().getIntent().getStringExtra("lastName");

            if(FirstName != null && LastName != null){

                name.setText(FirstName );
                Last.setText(LastName);

            }
        }
        return view;
    }
}
