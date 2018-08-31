package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DELL on 18/09/2017.
 */

public class ConferenceDbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private final static String DB_NAME="ConferenceDB.db";

    private static final String CREATE_TABLE="create table "+UserDatabase.TABLE2_NAME+
            " (id integer primary key autoincrement, "+
            UserDatabase.NAME+" text, "+
            UserDatabase.DESCRIPTION+" text, "+
            UserDatabase.LOCATION+" text, "+
            UserDatabase.INTEREST+" text, "+
            UserDatabase.PICTURE+" blob )";


    private static final String DROP_TABLE="DROP TABLE IF EXISTS " + UserDatabase.TABLE2_NAME;


    public ConferenceDbHelper(Context context){

      super(context,DB_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Nazim","conference table Creadted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

}
