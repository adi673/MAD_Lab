
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


#DB with spinner 
DatabaseHelper dbHelper = new DatabaseHelper(this);

// Fetch data from SQLite
List<String> moviesList = dbHelper.getAllMovies();
List<String> theatreList = dbHelper.getAllTheatres();

// Create Adapters
ArrayAdapter<String> adapterMovie = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moviesList);
ArrayAdapter<String> adapterTheatre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theatreList);

// Set dropdown style
adapterMovie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
adapterTheatre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Set adapters to Spinners
movieName.setAdapter(adapterMovie);
theatreName.setAdapter(adapterTheatre);

// Spinner selection handling
String selectedMovie = movieName.getSelectedItem().toString();
String selectedTheatre = theatreName.getSelectedItem().toString();

// Reset button
reset.setOnClickListener(view -> {
    movieName.setSelection(0);
    theatreName.setSelection(0);
});


public List<String> getAllMovies() {
    List<String> list = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT name FROM Movies", null);

    if (cursor.moveToFirst()) {
        do {
            list.add(cursor.getString(0));
        } while (cursor.moveToNext());
    }
    cursor.close();
    return list;
}

public List<String> getAllTheatres() {
    List<String> list = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT name FROM Theatres", null);

    if (cursor.moveToFirst()) {
        do {
            list.add(cursor.getString(0));
        } while (cursor.moveToNext());
    }
    cursor.close();
    return list;
}




//CalenderDialog and TimerDialog

<TextView
    android:id="@+id/selectedDate"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="#E0E0E0"
    android:padding="10dp"
    android:textSize="16sp"
    android:text="Select Date"
    android:clickable="true"
    android:focusable="true"
/>

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

#ListView
public class MainActivity extends AppCompatActivity {

    ListView myListView;
    String[] data = {"Apple", "Banana", "Cherry", "Mango", "Orange", "Strawberry", "Pineapple"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myListView = findViewById(R.id.myListView);

        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);

        // Set adapter to ListView
        myListView.setAdapter(adapter);

        // Handle item clicks
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = data[position];
                Toast.makeText(MainActivity.this, "You clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


#dynamic ListView
public class MainActivity extends AppCompatActivity {

    ListView myListView;
    ArrayAdapter<String> adapter;
    DBHelper dbHelper;
    ArrayList<String> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.myListView);
        dbHelper = new DBHelper(this);

        fruitList = dbHelper.getAllFruits();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruitList);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = fruitList.get(position);
            Toast.makeText(MainActivity.this, "You clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
        });
    }
}





#Menu
#optionsMenu 
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isAdminUser()) {
            menu.add(0, 1, Menu.NONE, "Admin Dashboard");
        } else {
            menu.add(0, 2, Menu.NONE, "User Profile");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                // Do something for Option 1
                return true;
            case 2:
                // Do something for Option 2
                return true;
            case 3:
                // Open Settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        userSpinner = findViewById(R.id.userSpinner);
        menuButton = findViewById(R.id.menuButton);

        loadUsersToSpinner();

        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
            Cursor cursor = dbHelper.getAllUsers();
            int idCol = cursor.getColumnIndex("id");
            int nameCol = cursor.getColumnIndex("name");

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idCol);
                String name = cursor.getString(nameCol);
                popupMenu.getMenu().add(Menu.NONE, id, Menu.NONE, name);
            }
            cursor.close();

            popupMenu.setOnMenuItemClickListener(item -> {
                int userId = item.getItemId();
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                return true;
            });

            popupMenu.show();
        });
    }

    private void loadUsersToSpinner() {
        usersList = new ArrayList<>();
        userIdMap = new HashMap<>();

        Cursor cursor = dbHelper.getAllUsers();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            usersList.add(name);
            userIdMap.put(name, id);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, usersList);
        userSpinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsersToSpinner(); // Refresh when returning from EditActivity
    }

#Context Menu
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        longClick = findViewById(R.id.longClick);
        registerForContextMenu(longClick);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }

    // ✅ Use onContextItemSelected() instead of onOptionsItemSelected()
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Toast.makeText(this, "Item1 Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item2) {
            Toast.makeText(this, "Item2 Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3) {
            Toast.makeText(this, "Item3 Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item4) {
            Toast.makeText(this, "Item4 Clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    #Dynamic  context
    @Override
    public class MainActivity extends AppCompatActivity {

        TextView longClick;
        SQLiteHelper dbHelper;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
            // Initialize the SQLiteHelper to interact with the database
            dbHelper = new SQLiteHelper(this);
    
            // Initialize the TextView for long-click
            longClick = findViewById(R.id.longClick);
    
            // Register for context menu
            registerForContextMenu(longClick);
        }
    
        // Create the context menu dynamically
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
    
            // Get dynamic menu items from the database
            List<String> menuItems = dbHelper.getMenuItems();
    
            // Add items to the context menu dynamically
            for (int i = 0; i < menuItems.size(); i++) {
                menu.add(0, i + 1, MenuItem.NONE, menuItems.get(i)); // MenuItem.NONE will be the default order
            }
        }
    
        // Handle the selection of context menu items
        @Override
        public boolean onContextItemSelected(MenuItem item) {
            int id = item.getItemId();
    
            // Check the id of the selected item and perform the corresponding action
            if (id >= 1) {
                String selectedOption = item.getTitle().toString();
                Toast.makeText(this, selectedOption + " clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return super.onContextItemSelected(item);
        }
    }

    
#options Menu 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar=findViewById(R.id.tbar);
        setSupportActionBar(toolbar);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.item1){
            Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.item2){
            Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.item3){
            Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

#dynamic options menu
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the SQLiteHelper to interact with the database
        dbHelper = new SQLiteHelper(this);

        // Set up toolbar
        toolbar = findViewById(R.id.tbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Fetch dynamic menu items from the database
        List<String> menuItems = dbHelper.getMenuItems();

        // Add dynamic menu items to the menu
        for (int i = 0; i < menuItems.size(); i++) {
            menu.add(Menu.NONE, i + 1, Menu.NONE, menuItems.get(i)); // Add items dynamically
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle the item click dynamically
        if (id >= 1) {
            String selectedItem = item.getTitle().toString();
            Toast.makeText(this, selectedItem + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

#Popup MEnu 
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

#dynamic popup 

public class MainActivity extends AppCompatActivity {

    Button popup;
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database helper
        dbHelper = new SQLiteHelper(this);

        popup = findViewById(R.id.popup);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        // Fetch menu items from the database
        List<String> menuItems = dbHelper.getMenuItems();

        // Dynamically add each menu item to the popup menu
        for (int i = 0; i < menuItems.size(); i++) {
            popupMenu.getMenu().add(0, i + 1, MenuItem.NONE, menuItems.get(i));
        }

        // Handle item selection
        popupMenu.setOnMenuItemClickListener(item -> {
            String selectedOption = item.getTitle().toString();
            Toast.makeText(this, selectedOption + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        });

        // Show the popup menu
        popupMenu.show();
    }
}

 
    





#Database Oeprations

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name  TEXT NOT NULL, " +
            " email TEXT NOT NULL UNIQUE, " +
            " number REAL NOT NULL );" 
        );
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

#insert 
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("id", 1);                    // Integer
    values.put("name", "Aditya");          // String
    values.put("cgpa", 9.1);               // Float/Double
    values.put("is_active", true);         // Boolean
    values.putNull("profile_picture");     // Null value (e.g., for a BLOB or text column)

    long rowId = db.insert("students", null, values);

    if (rowId != -1) {
        Log.d("DB", "Insert successful with ID: " + rowId);
    } else {
        Log.e("DB", "Insert failed");
    }
    db.close();

    // OR

    String query = "INSERT INTO students (name, cgpa) VALUES (?, ?)";
    db.execSQL(query, new Object[]{"John Doe", 8.7});


    
#delete
SQLiteDatabase db = dbHelper.getWritableDatabase();
    // To delete by an integer value (e.g., id = 5)
    int idToDelete = 5;
    db.delete("students", "id = ?", new String[]{String.valueOf(idToDelete)});
    
    // To delete by a String value (e.g., name = "Aditya")
    String nameToDelete = "Aditya";
    db.delete("students", "name = ?", new String[]{nameToDelete});
    
    // To delete by a REAL value (e.g., cgpa = 9.1)
    double cgpaToDelete = 9.1;
    db.delete("students", "cgpa = ?", new String[]{String.valueOf(cgpaToDelete)});

    int idToDelete = 5;
    db.execSQL("DELETE FROM students WHERE id = ?", new Object[]{idToDelete});

    int idToDelete = 5;
    String nameToDelete = "Aditya";
    // Using Object[] to pass both values
    db.execSQL("DELETE FROM students WHERE id = ? AND name = ?", new Object[]{idToDelete, nameToDelete});


    int rowsDeleted = db.delete("students","id = ? AND name = ?",new String[]{"5", "Aditya"});

    // OR
    String query = "DELETE FROM students WHERE id = ? AND name = ?";
    db.execSQL(query, new Object[]{5, "John Doe"});

 


#update
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    // Create a ContentValues object to hold the updated column values
    ContentValues contentValues = new ContentValues();
    contentValues.put("cgpa", 9.8);  // Set the new cgpa value

    // Perform the update with two conditions in the WHERE clause
    int rowsUpdated = db.update("students",contentValues,"id = ? AND name = ?",new String[]{"5", "Aditya"});

        // Check how many rows were updated
    if (rowsUpdated > 0) {
        Log.d("DB", "Updated " + rowsUpdated + " row(s) where id = 5 and name = Aditya");
    } else {
        Log.e("DB", "No rows updated where id = 5 and name = Aditya");
    }

    // OR

    // Update the name and cgpa for the student with id = 5
    String query = "UPDATE students SET name = ?, cgpa = ? WHERE id = ?";
    db.execSQL(query, new Object[]{"New Name", 9.5, 5});
    db.close();


    public int edit(String name, String email, int number) {
        SQLiteDatabase db = this.getWritableDatabase();
    
        // Fetch current values
        Cursor cursor = db.query(TABLE_NAME, null, "email = ?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String currentName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int currentNumber = cursor.getInt(cursor.getColumnIndexOrThrow("number"));
            cursor.close();
    
            // Use current values if new ones are not provided
            if (name == null || name.isEmpty()) {
                name = currentName;
            }
            if (number == 0) {
                number = currentNumber;
            }
    
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("number", number);
    
            return db.update(TABLE_NAME, cv, "email = ?", new String[]{email});
        }
    
        // If no record is found
        if (cursor != null) {
            cursor.close();
        }
        return 0;
    }
    


#Complex Query 

    // 1. Prepare the SQL query with the required parameters
    String query = "SELECT students.name, students.cgpa, courses.course_name " +
    "FROM students " +
    "JOIN courses ON students.id = courses.student_id " +
    "WHERE courses.course_name = ?";

    // 2. Execute the query and get a Cursor object
    Cursor cursor = db.rawQuery(query, new String[]{"Computer Science"});

    if (cursor.moveToFirst()) {
        // 3. Loop through the Cursor and extract data
        StringBuilder result = new StringBuilder(); // To store the result for display

        do {
            // Extract the data for each column in the current row
            String studentName = cursor.getString(cursor.getColumnIndex("name"));
            double cgpa = cursor.getDouble(cursor.getColumnIndex("cgpa"));
            String courseName = cursor.getString(cursor.getColumnIndex("course_name"));

            // 4. Format the data as needed (you can add more formatting here)
            result.append("Name: ").append(studentName)
            .append("\nCGPA: ").append(cgpa)
            .append("\nCourse: ").append(courseName)
            .append("\n\n");

        } while (cursor.moveToNext()); // Move to the next row

        // 5. Display the result in a TextView or any other UI component
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(result.toString()); // Set the formatted result in the TextView
    }
    cursor.close();


    // for spinner 
    public ArrayList<String> getAllTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    
        // Get column indices by column names
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("name");
        int dateIndex = cursor.getColumnIndex("date");
        int priorityIndex = cursor.getColumnIndex("priority");
    
        if (cursor.moveToFirst()) {
            do {
                // Use column indices instead of direct index access
                String task = cursor.getInt(idIndex) + "\n" +   // ID
                              cursor.getString(nameIndex) + "\n" +  // Name
                              cursor.getString(dateIndex) + "\n" +  // Date
                              cursor.getString(priorityIndex);       // Priority
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
    

#Shared Prefrences
public class MainActivity extends AppCompatActivity {

    
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserData";
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
       
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnSave.setOnClickListener(view -> saveData());

        // Clear data when "Clear" button is clicked
        btnClear.setOnClickListener(view -> clearData());
    }

    private void saveData() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String email = etEmail.getText().toString();

        // Save data in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("email", email);
        editor.apply();

        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    // Function to Load Saved Data
    private void loadData() {
        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String email = sharedPreferences.getString("email", "");

        etName.setText(name);
        etAge.setText(age);
        etEmail.setText(email);
    }

    // Function to Clear Data
    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        etName.setText("");
        etAge.setText("");
        etEmail.setText("");

        Toast.makeText(this, "Data Cleared!", Toast.LENGTH_SHORT).show();
    }
}

public class MainActivity extends AppCompatActivity {
    Spinner movieName;
    Spinner theatreName;
    TextView selectedDate;
    TextView selectedTime;
    ToggleButton ticket;
    Button submit;
    Button reset;
    Calendar calendar;
    int year;
    int month;
    int date;
    int hr;
    int min;
    int AP;
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
        movieName = findViewById(R.id.movieName);
        theatreName = findViewById(R.id.theatreName);
        selectedDate = findViewById(R.id.selectedDate);
        selectedTime = findViewById(R.id.selectedTime);
        ticket = findViewById(R.id.typeTicket);
        submit = findViewById(R.id.ButtonBook);
        reset = findViewById(R.id.ButtonReset);

        String[] movies = {"Movie1", "Movie2", "Movie3", "Movie4"};
        String[] theatres = {"T1", "T2", "T3", "T4", "T5"};

        ArrayAdapter<String> adapterMovie = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movies);
        ArrayAdapter<String> adapterTheatre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theatres);

        movieName.setAdapter(adapterMovie);
        theatreName.setAdapter(adapterTheatre);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        hr = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        updateDateDisplay();
        updateSubmitButtonState();

        // Date Picker
        selectedDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        updateDateDisplay();
                    }, year, month, date);
            datePickerDialog.show();
        });

        // Time Picker
        selectedTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute) -> {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
//                        StringBuilder str=new StringBuilder();
//                        StringBuilder str=new StringBuilder();
//                        str.append(hourOfDay);
//                        str.append(":");
//                        str.append(minute);
                        selectedTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        updateSubmitButtonState();
                    }, hr, min, false);
            timePickerDialog.show();
        });

        // Toggle button for ticket type
        ticket.setOnClickListener(view -> updateSubmitButtonState());

        // Book Now button
        submit.setOnClickListener(view -> {
            String movie = movieName.getSelectedItem().toString();
            String theatre = theatreName.getSelectedItem().toString();
            String date = selectedDate.getText().toString();
            String time = selectedTime.getText().toString();
            String ticketType = ticket.isChecked() ? "Premium" : "Standard";

            // Navigate to booking details page
            Intent intent = new Intent(MainActivity.this, BookingDetailsActivity.class);
            intent.putExtra("movie", movie);
            intent.putExtra("theatre", theatre);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            intent.putExtra("ticketType", ticketType);
            startActivity(intent);
        });

        // Reset button
        reset.setOnClickListener(view -> {
            movieName.setSelection(0);
            theatreName.setSelection(0);
            calendar = Calendar.getInstance();
            updateDateDisplay();
            selectedTime.setText("Select Time");
            ticket.setChecked(false);
            updateSubmitButtonState();
        });
    }
    private void updateDateDisplay() {
        selectedDate.setText(date + "/" + (month + 1) + "/" + year);
    }

    private void updateSubmitButtonState() {
        boolean isPremium = ticket.isChecked();
        boolean isAfterNoon = calendar.get(Calendar.HOUR_OF_DAY) >= 12;

        if (isPremium && !isAfterNoon) {
            submit.setEnabled(false);
        } else {
            submit.setEnabled(true);
        }
    }
}