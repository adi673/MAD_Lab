package com.example.options_menu;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int VERSION= 1;
    private String TABLE_NAME="Users";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name  TEXT NOT NULL, " +
                " email TEXT NOT NULL UNIQUE, " +
                " number REAL NOT NULL );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public int add(String name, String email, int number){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("number", number);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, cv);
        return result > 0 ? 1 : 0;
    }

    public int delete(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        int result=db.delete(TABLE_NAME, "email = ?", new String[]{email});
        return result;
    }

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



}

