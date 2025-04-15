package com.example.practfin;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "booking.db";
    private  static final int VERSION=1;
    private static final String PREF_NAME = "user_pref";
    private final SharedPreferences sharedPreferences;  // ✅ final, so must be initialized in constructor

    private String TABLE_USERS = "users";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table "+TABLE_USERS+"(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("INSERT INTO "+TABLE_USERS+"(username, password) VALUES ('admin', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
    }

    int verifyUser(String username, String password){
        SQLiteDatabase db=this.getReadableDatabase();
//        String query="SELECT * FROM "+TABLE_USERS+" WHERE username='"+username+"' AND password='"+password+"'";
        String query="select id, username from "+TABLE_USERS+" where username=? and password=?";
        Cursor cursor=db.rawQuery(query,new String[]{username,password});
        if(cursor.moveToFirst()){
            int userId=cursor.getInt(0);
            String u_name=cursor.getString(1);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("userId", userId);
            editor.putString("userName", u_name);
            editor.apply();
            return userId;
        }

        return -1;
    }
}
