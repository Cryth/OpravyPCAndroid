package com.example.splat.tarika_opravypc;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

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
        try{
            Cursor c = db.rawQuery(selector, args);
            c.moveToFirst();
            db.close();
            return c;
        }catch (Exception e){
            db.close();
            return new Cursor() {
                @Override
                public int getCount() {
                    return 0;
                }

                @Override
                public int getPosition() {
                    return 0;
                }

                @Override
                public boolean move(int i) {
                    return false;
                }

                @Override
                public boolean moveToPosition(int i) {
                    return false;
                }

                @Override
                public boolean moveToFirst() {
                    return false;
                }

                @Override
                public boolean moveToLast() {
                    return false;
                }

                @Override
                public boolean moveToNext() {
                    return false;
                }

                @Override
                public boolean moveToPrevious() {
                    return false;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean isBeforeFirst() {
                    return false;
                }

                @Override
                public boolean isAfterLast() {
                    return false;
                }

                @Override
                public int getColumnIndex(String s) {
                    return 0;
                }

                @Override
                public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
                    return 0;
                }

                @Override
                public String getColumnName(int i) {
                    return null;
                }

                @Override
                public String[] getColumnNames() {
                    return new String[0];
                }

                @Override
                public int getColumnCount() {
                    return 0;
                }

                @Override
                public byte[] getBlob(int i) {
                    return new byte[0];
                }

                @Override
                public String getString(int i) {
                    return null;
                }

                @Override
                public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {

                }

                @Override
                public short getShort(int i) {
                    return 0;
                }

                @Override
                public int getInt(int i) {
                    return 0;
                }

                @Override
                public long getLong(int i) {
                    return 0;
                }

                @Override
                public float getFloat(int i) {
                    return 0;
                }

                @Override
                public double getDouble(int i) {
                    return 0;
                }

                @Override
                public int getType(int i) {
                    return 0;
                }

                @Override
                public boolean isNull(int i) {
                    return false;
                }

                @Override
                public void deactivate() {

                }

                @Override
                public boolean requery() {
                    return false;
                }

                @Override
                public void close() {

                }

                @Override
                public boolean isClosed() {
                    return false;
                }

                @Override
                public void registerContentObserver(ContentObserver contentObserver) {

                }

                @Override
                public void unregisterContentObserver(ContentObserver contentObserver) {

                }

                @Override
                public void registerDataSetObserver(DataSetObserver dataSetObserver) {

                }

                @Override
                public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

                }

                @Override
                public void setNotificationUri(ContentResolver contentResolver, Uri uri) {

                }

                @Override
                public Uri getNotificationUri() {
                    return null;
                }

                @Override
                public boolean getWantsAllOnMoveCalls() {
                    return false;
                }

                @Override
                public void setExtras(Bundle bundle) {

                }

                @Override
                public Bundle getExtras() {
                    return null;
                }

                @Override
                public Bundle respond(Bundle bundle) {
                    return null;
                }
            };
        }
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
