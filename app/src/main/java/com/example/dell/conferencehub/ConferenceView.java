package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ConferenceView extends AppCompatActivity {

    TextView text;
    CheckBox checkBox;
    ImageView img;
    SQLiteDatabase conf_db,user_db;
    ConferenceDbHelper conf_helper;
    DataBaseHelper user_helper;
    Cursor cursor;
    int id1;
    String _fname,_description,_location,_interest;
    byte[] bytes;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if(checkBox.isChecked()){
                    ContentValues values=new ContentValues();
                    values.put(UserDatabase.CONFERENCE_ID,id1);
                    UserDatabase.UpdateProfile(UserDatabase.TABLE_NAME,values,user_db,user_helper,UserDatabase.userColumns);
                    Log.d("Nazim","User currently registred in this ocference db");
                }
                startActivity(new Intent(ConferenceView.this,SwipeActivity.class));
            }
        });

        cursor = conf_db.query(UserDatabase.TABLE2_NAME,UserDatabase.conferenceColumns,null,null,null,null,null,null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                id1 = cursor.getInt(cursor.getColumnIndex("id"));
                _fname = cursor.getString(cursor.getColumnIndex(UserDatabase.NAME));
                _description = cursor.getString(cursor.getColumnIndex(UserDatabase.DESCRIPTION));
                _location = cursor.getString(cursor.getColumnIndex(UserDatabase.LOCATION));
                _interest = cursor.getString(cursor.getColumnIndex(UserDatabase.INTEREST));
                bytes=cursor.getBlob(cursor.getColumnIndex(UserDatabase.PICTURE));
            }

            text.setText("id : "+id1+"\nname: "+_fname+"\ndescprition : "+_description+"\nlocation : "+_location+"\ninterest : "+_interest);
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if(bitmap!=null)img.setImageBitmap(bitmap);
        }

    }

    void init(){
        setContentView(R.layout.activity_conference_view);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        text=(TextView)findViewById(R.id.conf_text_id);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        img=(ImageView)findViewById(R.id.conf_img_id);
        conf_helper=new ConferenceDbHelper(this);
        conf_db=conf_helper.getWritableDatabase();
        user_helper=new DataBaseHelper(this);
        user_db=user_helper.getWritableDatabase();

    }

}
