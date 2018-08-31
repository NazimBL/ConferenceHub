package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Button login_b,facebook_b;
    TextView new_here,sign_b;
    EditText e_email,e_password;
    SQLiteDatabase user_db,conference_db;
    DataBaseHelper user_dbHelper;
    ConferenceDbHelper conference_dbHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();

        login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=e_email.getText().toString();
                String password=e_password.getText().toString();
                cursor = user_db.rawQuery("SELECT *FROM "+UserDatabase.TABLE_NAME+" WHERE "+UserDatabase.EMAIL+"=? AND "+UserDatabase.PASSWORD+"=?",new String[] {email,password});

                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
//                        String _fname = cursor.getString(cursor.getColumnIndex(UserDatabase.));
//                        String _email = cursor.getString(cursor.getColumnIndex(UserDatabase.));
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Home.class));
                    }else Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();;

            }
//
        });
        sign_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        writeDummyConferenceDatabase();


    }
    void initUi(){

        setContentView(R.layout.activity_main);
        sign_b=(TextView)findViewById(R.id.sign_up);
        login_b=(Button)findViewById(R.id.login_id);
        new_here=(TextView)findViewById(R.id.new_here);

        e_email=(EditText)findViewById(R.id.email);
        e_password=(EditText)findViewById(R.id.password);
        sign_b.setPaintFlags(sign_b.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        facebook_b=(Button)findViewById(R.id.fb_id);

        user_dbHelper=new DataBaseHelper(this);
        user_db=user_dbHelper.getReadableDatabase();

        conference_dbHelper=new ConferenceDbHelper(this);
        conference_db=conference_dbHelper.getWritableDatabase();
    }
    void writeDummyConferenceDatabase(){
//        dbHelper=new DataBaseHelper(this);

        ContentValues values=new ContentValues();
        values.put(UserDatabase.NAME,"Ethics of AI");
        values.put(UserDatabase.DESCRIPTION,"this is some description");
        values.put(UserDatabase.LOCATION,"Amsterdam");
        values.put(UserDatabase.INTEREST,"TIC");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ai);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

        values.put(UserDatabase.PICTURE,bos.toByteArray());

        conference_db.insert(UserDatabase.TABLE2_NAME,null,values);

       // UserDatabase.compressBitmapToDb(icon,db,dbHelper,UserDatabase.TABLE2_NAME,UserDatabase.conferenceColumns);

    }
}
