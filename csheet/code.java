
// RadioGroup
RadioGroup Rgroup;
Rgroup=findViewById(R.id.radioGroup);
Rgroup.setOnCheckedChangeListener((grp,checkedId)->{
    if(checkedId !=-1){
        RadioButton selectedButton=findViewById(checkedId);
        String val=selectedButton.getText().toString();
        submit.setText(val);
    }
});
Rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton btn=findViewById(i);
        String val=btn.getText().toString();
        submit.setText(val);
    }
});
;


//Spinner
Spinner movieName;
Spinner theatreName;
movieName = findViewById(R.id.movieName);
theatreName = findViewById(R.id.theatreName);
String[] movies = {"Movie1", "Movie2", "Movie3", "Movie4"};
String[] theatres = {"T1", "T2", "T3", "T4", "T5"};
ArrayAdapter<String> adapterMovie = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movies);
ArrayAdapter<String> adapterTheatre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theatres);
movieName.setAdapter(adapterMovie);
theatreName.setAdapter(adapterTheatre);
String movie = movieName.getSelectedItem().toString();
String theatre = theatreName.getSelectedItem().toString();
reset.setOnClickListener(view -> {
    movieName.setSelection(0);
    theatreName.setSelection(0);
})


//CalenderDialog and TimerDialog
calendar = Calendar.getInstance();
year = calendar.get(Calendar.YEAR);
month = calendar.get(Calendar.MONTH);
date = calendar.get(Calendar.DAY_OF_MONTH);
hr = calendar.get(Calendar.HOUR_OF_DAY);
min = calendar.get(Calendar.MINUTE);
selectedDate.setOnClickListener(view -> {
    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
            (view1, year, month, dayOfMonth) -> {
                calendar.set(year, month, dayOfMonth);
                updateDateDisplay();
            }, year, month, date);
    datePickerDialog.show();
});
private void updateDateDisplay() {
    selectedDate.setText(date + "/" + (month + 1) + "/" + year);
}

// Time Picker
selectedTime.setOnClickListener(view -> {
    TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
            (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                StringBuilder str=new StringBuilder();
                StringBuilder str=new StringBuilder();
                str.append(hourOfDay);
                str.append(":");
                str.append(minute);
                selectedTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                updateSubmitButtonState();
            }, hr, min, false);
    timePickerDialog.show();
});



// Intent
Intent intent = new Intent(MainActivity.this, BookingDetailsActivity.class);
intent.putExtra("movie", movie);
intent.putExtra("theatre", theatre);
intent.putExtra("date", date);
intent.putExtra("time", time);
intent.putExtra("ticketType", ticketType);
startActivity(intent);


Intent int1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
startActivity(int1);

public class secondClass extends AppCompatActivity {
    TextView textView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView= findViewById(R.id.textView);
        String receivedMessage= getIntent().getStringExtra("message");

        if (receivedMessage != null) {
            textView.setText(receivedMessage);
        } else {
            textView.setText("No message received");
        }
    }
}

//seekBar
SeekBar bar;
bar=findViewById(R.id.bar_seek);
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



//checkBox
CheckBox b1;
CheckBox b2;
CheckBox b3;
b1=findViewById(R.id.box1);
b2=findViewById(R.id.box2);
b3=findViewById(R.id.box3);
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
//       b1.toggle()


//switch
private Switch switch1;
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


//Custom Toast
public class MainActivity extends AppCompatActivity {

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

        showCustomToast("Welcome to Custom Toast!", R.drawable.success);
    }

    private void showCustomToast(String message, int imageResource) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toast_root));

        ImageView imageView = layout.findViewById(R.id.toast_image);
        imageView.setImageResource(imageResource);

        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
  //toast_layout.xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@+id/toast_root"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:padding="10dp"
  android:background="@drawable/toast_background"
  android:orientation="horizontal">

  <ImageView
      android:id="@+id/toast_image"
      android:layout_width="40dp"
      android:layout_height="40dp"
      android:src="@drawable/success" />

  <TextView
      android:id="@+id/toast_text"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Custom Toast Message"
      android:textColor="@android:color/white"
      android:textSize="16sp"
      android:paddingStart="10dp"/>
</LinearLayout>

 //toast background.xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <corners android:radius="16dp"/>
    <solid android:color="#333333"/>
    <padding android:left="10dp" android:top="10dp" android:right="10dp" android:bottom="10dp"/>
</shape>




//Table Talyout
<TableLayout
        android:id="@+id/tableLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:stretchColumns="*"
        android:background="@android:color/darker_gray"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <!-- Header Row -->
        <TableRow>
            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Header 1"
                android:textStyle="bold"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Header 2"
                android:textStyle="bold"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Header 3"
                android:textStyle="bold"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>
        </TableRow>

        <!-- Data Row -->
        <TableRow>
            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Data 1"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Data 2"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Data 3"
                android:gravity="center"
                android:padding="8dp"
                android:background="@drawable/table_cell_border"/>
        </TableRow>

    </TableLayout>
No file chosenNo file chosen