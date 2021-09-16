package com.example.coffeeapp01;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class coffeeDBHandler extends SQLiteOpenHelper {
    public static final String TABLE_COFFEE = "coffeesales";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CUSTOMERNAME="customername";
    public static final String COLUMN_SALEAMOUNT = "saleamount";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coffeebase.db";


    public coffeeDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_COFFEE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMERNAME + " TEXT , " +
                COLUMN_SALEAMOUNT + " INTEGER " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_COFFEE);
        onCreate(db);

    }

    public void addOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMERNAME, order.get_custName());
        values.put(COLUMN_SALEAMOUNT, order.get_saleAmount());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COFFEE, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COFFEE + " WHERE 1 ";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME))!=null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME)) + " ==>> $" +
                        c.getString(c.getColumnIndex(COLUMN_SALEAMOUNT));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}

