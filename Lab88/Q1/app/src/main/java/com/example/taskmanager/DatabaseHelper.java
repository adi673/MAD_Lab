package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Tasks";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_DATE = "due_date";
    private static final String COL_PRIORITY = "priority";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_DATE + " TEXT, " +
                COL_PRIORITY + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ✅ Add a new task
    public void addTask(String name, String date, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DATE, date);
        values.put(COL_PRIORITY, priority);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // ✅ Get all tasks
    public ArrayList<String> getAllTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                // Append the ID to the task string for proper referencing
                String task = cursor.getInt(0) + "\n" +   // ID
                        cursor.getString(1) + "\n" +      // Name
                        cursor.getString(2) + "\n" +      // Date
                        cursor.getString(3);              // Priority
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    // ✅ Update a task by ID
    public void updateTask(int id, String name, String date, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DATE, date);
        values.put(COL_PRIORITY, priority);

        db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // ✅ Delete a task by ID
    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
