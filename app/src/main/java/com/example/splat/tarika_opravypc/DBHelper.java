package com.example.splat.tarika_opravypc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by splat on 31.3.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MojaDat";

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE " +
            MojaDat.Customers.TABLE_NAME + "(" +
            MojaDat.Customers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MojaDat.Customers.COLUMN_NAME + " TEXT, " +
            MojaDat.Customers.COLUMN_EMAIL + " TEXT )";
    private static final String CREATE_TABLE_COMPUTERS = "CREATE TABLE " +
            MojaDat.Computers.TABLE_NAME + "(" +
            MojaDat.Computers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MojaDat.Computers.COLUMN_CID + " INTEGER, " +
            MojaDat.Computers.COLUMN_BRAND + " TEXT, " +
            MojaDat.Computers.COLUMN_MODEL + " TEXT )";
    private static final String CREATE_TABLE_REPAIRS = "CREATE TABLE " +
            MojaDat.Repairs.TABLE_NAME + "(" +
            MojaDat.Repairs.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MojaDat.Repairs.COLUMN_COID + " INTEGER, " +
            MojaDat.Repairs.COLUMN_DATE + " TEXT, " +
            MojaDat.Repairs.COLUMN_OBJECT + " TEXT, " +
            MojaDat.Repairs.COLUMN_ABOUT + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
