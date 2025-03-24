package com.example.q3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MOVIE_DATABASE";
    private static final int VERSION = 1;
    private static final String TABLE_MOVIES = "Movies";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MOVIES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "year INTEGER, " +
                "rating INTEGER)";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public void addReview(String name, int year, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("year", year);
        values.put("rating", rating);

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    // Get all movie names
    public ArrayList<String> getAllMovies() {
        ArrayList<String> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_MOVIES, null);

        if (cursor.moveToFirst()) {
            do {
                movies.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    // Get movie details by name
    public Cursor getMovieDetails(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MOVIES + " WHERE name=?", new String[]{name});
    }
}
