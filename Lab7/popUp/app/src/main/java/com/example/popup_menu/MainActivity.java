package com.example.popup_menu;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button popup;
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
        popup=findViewById(R.id.popup);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }
    public void showPopupMenu(View view){
        PopupMenu popupmenu=new PopupMenu(this,view);
        MenuInflater inflater=popupmenu.getMenuInflater();
        inflater.inflate(R.menu.menu_main,popupmenu.getMenu());

        popupmenu.setOnMenuItemClickListener(v->{
            int itemid=v.getItemId();
            if(itemid==R.id.action_settings){
                //handle settings click
                Toast.makeText(this,"Settings Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            else if(itemid==R.id.action_search){
                //handle search click
                Toast.makeText(this,"Search Clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemid==R.id.action_share) {
                //handle share click
                Toast.makeText(this,"Share Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });


        popupmenu.show();

    }

}