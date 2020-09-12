package com.example.trippleattassignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStore extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "app";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "student";
    private static final String NAME = "name";
    private static final String FNAME = "fname";
    public static final String MOBNO = "mobno";
    private static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public DataStore(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "create table "+TABLE_NAME+"("+NAME+" text,"+FNAME+" text,"+ MOBNO+" text,"+
                EMAIL+" text primary key,"+PASSWORD+" text);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        String DROP_TABLE = "drop table if exists "+TABLE_NAME+";";

        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean addDataInTable(String n,String f,String m,String e,String p,SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, n);
        contentValues.put(FNAME, f);
        contentValues.put(MOBNO, m);
        contentValues.put(EMAIL, e);
        contentValues.put(PASSWORD, p);

        long res = database.insert(TABLE_NAME, null, contentValues);
        return res!=-1;
    }
    public Cursor readData(String e)
    {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] projections = {EMAIL,PASSWORD};
        cursor = sqLiteDatabase.query(TABLE_NAME,projections, EMAIL +" LIKE ? ",new String[]{e},null,null,null,null);

        return cursor;
    }

}


