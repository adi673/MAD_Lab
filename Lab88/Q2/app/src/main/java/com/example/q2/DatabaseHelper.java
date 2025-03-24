package com.example.q2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="groceryDB";
    private static final int DATABASE_VERSION=1;
    private  static final String TABLE_GROCERY="Grocery";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable=" CREATE TABLE "+TABLE_GROCERY+" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cost REAL)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_GROCERY);
        onCreate(sqLiteDatabase);
    }

    public void addGroceryItem(String name, double cost){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("cost",cost);

        db.insert(TABLE_GROCERY,null,values);
        db.close();
    }

    public ArrayList<String> getAllGroceryItems(){
        ArrayList<String> items=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * FROM "+TABLE_GROCERY,null);

        if(cursor.moveToFirst()){
            do{
                String item=cursor.getString(1)+" -₹"+cursor.getDouble(2);
                items.add(item);
            }while ((cursor.moveToNext()));
        }
        cursor.close();
        db.close();
        return items;
    }

    public double getTotalCost(ArrayList<String> selectedItems) {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        // ✅ FIX: Clear duplicate items by using HashSet
        HashSet<String> uniqueItems = new HashSet<>(selectedItems);

        for (String item : uniqueItems) {  // Use unique items only
            String[] parts = item.split(" -₹");  // ✅ FIX: Adjusted split pattern
            String itemName = parts[0].trim();

            Cursor cursor = db.rawQuery("SELECT cost FROM " + TABLE_GROCERY + " WHERE name=?", new String[]{itemName});
            if (cursor.moveToFirst()) {
                total += cursor.getDouble(0);
            }
            cursor.close();
        }
        db.close();
        return total;
    }

}
