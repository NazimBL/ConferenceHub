package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText e_name,e_email,e_pass,e_age,e_linkdin;
    Button done;
    CheckBox tic,economy,social,health;
    SQLiteDatabase db;
    DataBaseHelper dbHelper;
    String interests="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=e_name.getText().toString();
                String email=e_email.getText().toString();
                String password=e_pass.getText().toString();
                String linkdin=e_linkdin.getText().toString();
                int age=Integer.parseInt(e_age.getText().toString());
                checkInterests();
                UserDatabase.InsertData(db,name,email,password,age,linkdin,interests,0,1);
                startActivity(new Intent(RegisterActivity.this,PictureActivity.class));
            }
        });

    }
    void initUi(){
        setContentView(R.layout.activity_register);
        economy=(CheckBox) findViewById(R.id.female);
        tic=(CheckBox) findViewById(R.id.male);
        social=(CheckBox) findViewById(R.id.social);
        health=(CheckBox) findViewById(R.id.health);

        e_email=(EditText)findViewById(R.id.email);
        e_pass=(EditText)findViewById(R.id.password);
        e_name=(EditText)findViewById(R.id.name);
        e_age=(EditText) findViewById(R.id.age);
        e_linkdin=(EditText) findViewById(R.id.linkdin);

        done=(Button)findViewById(R.id.done);
        dbHelper=new DataBaseHelper(this);
        db=dbHelper.getWritableDatabase();

    }
    void checkInterests(){
        interests="";
        if(economy.isChecked())interests+="economy,";
        if(social.isChecked())interests+="social,";
        if(tic.isChecked())interests+="tic,";
        if(health.isChecked())interests+="health,";
    }


}
