package com.example.lab7_b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CITY";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "nameCity";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    ArrayList<City> arrayList = new ArrayList<>();




    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_citys_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME);
        db.execSQL(create_citys_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_citys_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_citys_table);

        onCreate(db);
    }
    public ArrayList<City> getAllStudents() {
        ArrayList<City>  cityList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            City city = new City(cursor.getInt(0), cursor.getString(1));
            cityList.add(city);
            cursor.moveToNext();
        }
        return cityList;
    }
    public void saveCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, city.getId());
        values.put(KEY_NAME, city.getNameCity());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, city.getNameCity());


        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(city.getId()) });
        db.close();
    }
    public void deleteStudent(int cityId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(cityId)});
        db.close();
    }
}
