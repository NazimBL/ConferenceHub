package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView text;
    SQLiteDatabase db;
    DataBaseHelper helper;
    Cursor cursor;
    String _fname,_email,_pass,_mobile;
    int id1,age,conf_id=0;
    byte[] bytes;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileInit();

        readUserDb();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put(UserDatabase.CONFERENCE_ID,45);
                UserDatabase.UpdateProfile(UserDatabase.TABLE_NAME,values,db,helper,UserDatabase.userColumns);
                readUserDb();

            }
        });

    }
    void profileInit(){
        setContentView(R.layout.activity_profile);
        text=(TextView)findViewById(R.id.profile_text);
        img=(ImageView)findViewById(R.id.img);

        helper=new DataBaseHelper(this);
        db=helper.getWritableDatabase();

    }
    void readUserDb(){
        cursor = db.query(UserDatabase.TABLE_NAME,UserDatabase.userColumns,null,null,null,null,null,null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                id1 = cursor.getInt(cursor.getColumnIndex("id"));
                _fname = cursor.getString(cursor.getColumnIndex(UserDatabase.NAME));
                age = cursor.getInt(cursor.getColumnIndex(UserDatabase.AGE));
                _email = cursor.getString(cursor.getColumnIndex(UserDatabase.EMAIL));
                _pass = cursor.getString(cursor.getColumnIndex(UserDatabase.PASSWORD));
                _mobile = cursor.getString(cursor.getColumnIndex(UserDatabase.INTERESTS));
                bytes=cursor.getBlob(cursor.getColumnIndex(UserDatabase.PICTURE));
                conf_id=cursor.getInt(cursor.getColumnIndex(UserDatabase.CONFERENCE_ID));


            }
            text.setText("id : "+id1+"\nname : "+_fname+"\nemail : "
                                +_email+"\npass : "+_pass
                                +"\ninterests : "+_mobile+"\n conf id : "+conf_id);
            if(bytes!=null){
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img.setImageBitmap(bitmap);
            }


        }
    }
}
