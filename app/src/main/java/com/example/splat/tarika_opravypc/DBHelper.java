package com.example.splat.tarika_opravypc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_COMPUTERS);
        db.execSQL(CREATE_TABLE_REPAIRS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+MojaDat.Customers.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+MojaDat.Computers.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+MojaDat.Repairs.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCursor(String selector, String[] args){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(selector, args);
        c.moveToFirst();
        db.close();
        return c;
    }

    public void addCustomer(Customer c){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MojaDat.Customers.COLUMN_NAME, c.getMeno());
        values.put(MojaDat.Customers.COLUMN_EMAIL, c.getEmail());

        db.insert(MojaDat.Customers.TABLE_NAME, null, values);
        db.close();
    }
    public void addComputer(Computer comp){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MojaDat.Computers.COLUMN_CID, comp.getcID());
        values.put(MojaDat.Computers.COLUMN_BRAND, comp.getZnacka());
        values.put(MojaDat.Computers.COLUMN_MODEL, comp.getModel());

        db.insert(MojaDat.Computers.TABLE_NAME, null, values);
        db.close();
    }
    public void addRepair(Repair r){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MojaDat.Repairs.COLUMN_COID, r.getCoID());
        values.put(MojaDat.Repairs.COLUMN_DATE, r.getDatum());
        values.put(MojaDat.Repairs.COLUMN_OBJECT, r.getPredmet());
        values.put(MojaDat.Repairs.COLUMN_ABOUT, r.getPopis());

        db.insert(MojaDat.Repairs.TABLE_NAME, null, values);
        db.close();
    }


}
