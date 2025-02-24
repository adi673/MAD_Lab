package com.example.practise_tab_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class viewFragment extends Fragment {
    TextView name;

    TextView lastName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment, container, false);
        name=view.findViewById(R.id.name);
        lastName=view.findViewById(R.id.lastName);


        if(getActivity() != null ){
            String FirstName= getActivity().getIntent().getStringExtra("name");
            String LastName = getActivity().getIntent().getStringExtra("lastName");

            if(FirstName != null && LastName !=null){
                name.setText(FirstName);
                lastName.setText(LastName);
            }

        }
        return view;
    }
}
