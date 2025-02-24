package com.example.practise_tab_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class infofragment extends Fragment {

    public EditText name;

    public EditText last;

    public Button Submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.info_fragment, container, false);
        name=view.findViewById(R.id.editText);
        last=view.findViewById(R.id.editText2);
        Submit=view.findViewById(R.id.click);

        Submit.setOnClickListener(v->{
            String StartName= name.getText().toString();
            String LastName= last.getText().toString();

            Intent intent=new Intent(getActivity(), MainActivity.class);
            intent.putExtra("name",StartName);
            intent.putExtra("lastName",LastName);
            startActivity(intent);
        });

        return view;
    }
}
