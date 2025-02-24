package com.example.lab4;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ZoomButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SeekBar bar;
    EditText text;
    Button bt;

    CheckBox b1;
    CheckBox b2;
    CheckBox b3;
    int Progress;
    ZoomButton zb;
    private ToggleButton toggleButton;
    private Switch switch1;
    private TextView statusText;
    private Button submittButton;
    private TextView resultText;
    private RadioGroup RadioGrp;
    private Button move;
    private Button move2;
    boolean i=true;
    @SuppressLint("MissingInflatedId")
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
        text=findViewById(R.id.progress);
        bar=findViewById(R.id.bar_seek);
        b1=findViewById(R.id.box1);
        b2=findViewById(R.id.box2);
        b3=findViewById(R.id.box3);
        StringBuilder result=new StringBuilder("Result : ");
        Progress=0;
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Triggered when progress changes
                Progress=progress;
                text.setText(String.valueOf(Progress));
                Toast.makeText(MainActivity.this, "Progress: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Triggered when user starts dragging
                Toast.makeText(MainActivity.this, "Tracking Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Triggered when user stops dragging

                Toast.makeText(MainActivity.this, "Tracking Stopped", Toast.LENGTH_SHORT).show();
            }
        });


        bt=findViewById(R.id.but);
        bt.setOnClickListener(v->{
//            Toast.makeText(getApplicationContext(), "Button Clicked!", Toast.LENGTH_SHORT).show();
            //or
            //Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();
            //or
            //Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i){
                    bt.setText("Hello");
                    i=!i;
                }else{
                    bt.setText("Click Me");
                    i=!i;
                }
                Toast.makeText(getApplicationContext(), "Button Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        b1.setOnCheckedChangeListener(((compoundButton, b) -> {
            if(b){
                Toast.makeText(getApplicationContext(),"Button checkedd",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Button Unchecked", Toast.LENGTH_SHORT).show();
            }
        }));

        b2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this,"button 2 checked",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"button 2 Unchecked",Toast.LENGTH_SHORT).show();
                }

            }
        });

        b3.setOnCheckedChangeListener((butttonView, isChecked)->{

            if(isChecked){
                result.setLength(9);
                result.append("Button 3 checked ");
                Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
            }else{
                result.setLength(9);
                result.append("Button 3 Unchecked ");
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//       b1.setChecked(false);
//       b1.setChecked(true);
//       b1.toggle();

        zb=findViewById(R.id.zoomButton);
        zb.setOnClickListener(v ->
                Toast.makeText(this, "Zoom Button Clicked!", Toast.LENGTH_SHORT).show()
        );

        zb.setVisibility(ZoomButton.VISIBLE);

        toggleButton = findViewById(R.id.toggleButton);
        statusText = findViewById(R.id.statusText);

        // Set initial status text based on the toggle state


        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                statusText.setText("Toggle Status: ON");
            } else {
                statusText.setText("Toggle Status: OFF");
            }
        });

        switch1=findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    statusText.setText("Toggle switch Status: ON");
                }else{
                    statusText.setText("Toggle switch  Status: OFF");
                }
            }
        });

        RadioGrp=findViewById(R.id.radioGroup);
        submittButton=findViewById(R.id.submitButton);
        resultText=findViewById(R.id.resultText);

        submittButton.setOnClickListener(v->{
            int seletectedId=RadioGrp.getCheckedRadioButtonId();
            if(seletectedId != -1){
                RadioButton selectedButton=findViewById(seletectedId);
                resultText.setText("Selected option : "+selectedButton.getText());
            }else{
                resultText.setText(" Not seleted ");
            }
        });



        RadioGrp.setOnCheckedChangeListener((grp,checkedId)->{
            if (checkedId != -1) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                resultText.setText("You selected: " + selectedRadioButton.getText());
            }
        });

//        RadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//            }
//        });
        move=findViewById(R.id.move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent int1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
              startActivity(int1);
            }
        });

        move2=findViewById(R.id.move2);
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, secondClass.class);
                intent.putExtra("message","Hii hello from First pageto second page");
                startActivity(intent);
            }
        });

    }

}