package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private final static String DB_NAME="UserDB.db";

    private static final String CREATE_TABLE="create table if not exists "+UserDatabase.TABLE_NAME+
                                              " (id integer primary key autoincrement, "+
                                                UserDatabase.NAME+" text, "+
                                                UserDatabase.AGE+" integer, "+
                                                UserDatabase.EMAIL+" text, "+
                                                UserDatabase.PASSWORD+" text, "+
                                                UserDatabase.SYNC_STATUS+" integer, "+
                                                UserDatabase.CONFERENCE_ID+" integer, "+
                                                UserDatabase.PICTURE+" blob, "+
                                                UserDatabase.INTERESTS+" text, "+
                                                UserDatabase.LINKDIN+" text )";

    private static final String DROP_TABLE="DROP TABLE IF EXISTS " + UserDatabase.TABLE_NAME;


    public DataBaseHelper(Context context){

        super(context,DB_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Nazim","user table Creadted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

}
