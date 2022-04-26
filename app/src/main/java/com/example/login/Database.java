package com.example.login;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String Database_Name = "DATA_BASE.db";
    String create_Table = "CREATE TABLE DATA(ID TEXT, PASS TEXT)";
    String update_Table = "DROP TABLE IF EXISTS DATA";
    String select = "SELECT * FROM DATA";

    public Database(Context context, int version) {
        super(context, Database_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(update_Table);
        onCreate(sqLiteDatabase);
    }

    public void Insert(String id, String password) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(addInsert(id ,password));
        database.close();
    }

    public ArrayList<Member> getResult() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Member> list = new ArrayList<>();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        while (cursor.moveToNext()) {
            Member member = new Member(cursor.getString(0), cursor.getString(1));
            list.add(member);
        }
        return list;
    }

    private String addInsert(String id, String pass) {
        return "INSERT INTO DATA VALUES(" + id + ", '" + pass + "')";
    }
}
