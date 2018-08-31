package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by DELL on 18/09/2017.
 */

public class UserDatabase {
    public final static int SYNC_OK=1;
    public final static int SYNC_FAILED=0;

    public final static String TABLE_NAME="User";
    public final static String NAME="name";
    public final static String AGE="age";
    public final static String EMAIL="email";
    public final static String PASSWORD="password";
    public final static String LINKDIN="linkdin";
    public final static String PICTURE="picture";
    public final static String INTERESTS="interests";
    public final static String SYNC_STATUS="status";
    public final static String CONFERENCE_ID="conference_id";

    public final static String[] userColumns = {
            "id",
            NAME,
            AGE,
            EMAIL,
            PASSWORD,
            SYNC_STATUS,
            CONFERENCE_ID,
            INTERESTS,
            LINKDIN,
            PICTURE

    };


    public final static String TABLE2_NAME="Conference";

    public final static String DESCRIPTION="description";
    public final static String LOCATION="location";
    public final static String INTEREST="interest";

    public final static String[] conferenceColumns = {
            "id",
            NAME,
            DESCRIPTION,
            LOCATION,
            INTEREST,
            PICTURE
    };

public static void compressBitmapToDb(Bitmap bitmap, SQLiteDatabase sqLiteDatabase,DataBaseHelper helper,String tableName,String[] columns){
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
    ContentValues values=new ContentValues();
    values.put(PICTURE,bos.toByteArray());
    UpdateProfile(tableName,values,sqLiteDatabase,helper,columns);

    //Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
}
    public static void InsertData(SQLiteDatabase db,String name,String email,String password,int age,String link,String ints,int sync,int conf){
        ContentValues values=new ContentValues();
        values.put(UserDatabase.NAME,name);
        values.put(UserDatabase.EMAIL,email);
        values.put(UserDatabase.PASSWORD,password);
        values.put(UserDatabase.AGE,age);
        values.put(UserDatabase.SYNC_STATUS,sync);
        values.put(UserDatabase.LINKDIN,link);
        values.put(UserDatabase.INTERESTS,ints);
        values.put(UserDatabase.CONFERENCE_ID,conf);
        db.insert(UserDatabase.TABLE_NAME,null,values);

    }
    public static int UpdateProfile(String tableName,ContentValues values, SQLiteDatabase db, DataBaseHelper helper,String[] columns) {

        db = helper.getReadableDatabase();

        int userid=0;
        Cursor cursor=db.query(tableName,columns,null,null,null,null,null,null);
        if(cursor.getCount()>0){

            while(cursor.moveToNext()) userid = cursor.getInt(cursor.getColumnIndex("id"));
        }

        // Which row to update, based on the userid column
        String selection =  "id = ?";
        String[] selectionArgs = { String.valueOf(userid) };

        int count = db.update(
                tableName,
                values,
                selection,
                selectionArgs);

        //Return how many rows updated
        return count;
    }
    public static User findUser(String table,String column,String searchThis,DataBaseHelper helper){
        String query = "Select * FROM " + table + " WHERE " + column
                + " = \"" + searchThis + "\"";

        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user=new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setLinkdin(cursor.getString(9));
            user.setBytes(cursor.getBlob(7));
            cursor.close();
        } else {
            Log.d("Nazim","Nothing found");
            user=null;
        }
        db.close();

return user;

    }
    public static void getUsers(String table, String column, String searchThis, DataBaseHelper helper, ArrayList list){
        String query = "Select * FROM " + table + " WHERE " + column
                + " = \"" + searchThis + "\"";

        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user=new User();
        byte i=0;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
//                user.setId(Integer.parseInt(cursor.getString(0)));
//                user.setName(cursor.getString(1));
//                user.setLinkdin(cursor.getString(9));
//                user.setBytes(cursor.getBlob(7));
                list.add(cursor.getString(cursor.getColumnIndex("name")));
                list.add(i,user);
                i++;
                cursor.moveToNext();
            }
            cursor.close();
        } else {
            Log.d("Nazim","Nothing found");
            user=null;
        }
        db.close();



    }


}
