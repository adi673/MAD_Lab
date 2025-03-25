public package com.example.studentmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    // Constants for database name and version
    private static final String DATABASE_NAME = "student_management.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_ROLL_NUMBER = "roll_number";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MARKS = "marks";

    // SQL query to create the students table
    private static final String CREATE_TABLE_STUDENTS =
            "CREATE TABLE " + TABLE_STUDENTS + " (" +
                    COLUMN_ROLL_NUMBER + " VARCHAR PRIMARY KEY, " +
                    COLUMN_NAME + " VARCHAR, " +
                    COLUMN_MARKS + " VARCHAR);";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    // Add student method - do NOT close the database inside
    public boolean addStudent(String rollNumber, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();  // Opening database for writing
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLL_NUMBER, rollNumber);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MARKS, marks);

        long result = db.insert(TABLE_STUDENTS, null, values);
        return result != -1;
    }

    // Delete student method
    public boolean deleteStudent(String rollNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_STUDENTS, COLUMN_ROLL_NUMBER + "=?", new String[]{rollNumber});
        return rowsAffected > 0;
    }

    // Update student method
    public boolean updateStudent(String rollNumber, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MARKS, marks);

        int rowsAffected = db.update(TABLE_STUDENTS, values, COLUMN_ROLL_NUMBER + "=?", new String[]{rollNumber});
        return rowsAffected > 0;
    }

    // Get all students method
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
    }

    // Close the database outside of the method where operations occur.
    public void closeDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}

 DbHelper {
    
}
