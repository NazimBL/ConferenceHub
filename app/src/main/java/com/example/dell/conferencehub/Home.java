package com.example.dell.conferencehub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] dummydata={"HolyTech Festival","WHO conference on health","AI Ethics"};
    String[] dummydata2={"Algerian best Tech Event","blablablabla","arificial intelligence and deeplearning fondations"};

    String[] split=new String[5];
    String interests;

    SQLiteDatabase u_db,conf_db;
    DataBaseHelper u_helper;
    ConferenceDbHelper conf_helper;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        fetchInterests();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.profile_menu)startActivity(new Intent(Home.this,ProfileActivity.class));
        return super.onOptionsItemSelected(item);
    }
    void initAdapter(String[] a1,String[] a2){
        adapter=new MyRecyclerAdapter(a1,a2,Home.this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    void init(){
        setContentView(R.layout.activity_home);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_id);
        initAdapter(dummydata,dummydata2);
        u_helper=new DataBaseHelper(Home.this);
        u_db=u_helper.getReadableDatabase();
        conf_helper=new ConferenceDbHelper(Home.this);
        conf_db=conf_helper.getReadableDatabase();
    }
    void fetchInterests(){
        readUserDb();
        split=interests.split(",");
        byte i=0;
        while(i<split.length){
            Log.d("Nazim","interests :"+split[i]);
            i++;
        }

    }
    void readUserDb(){
        cursor = u_db.query(UserDatabase.TABLE_NAME,UserDatabase.userColumns,null,null,null,null,null,null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                interests = cursor.getString(cursor.getColumnIndex(UserDatabase.INTERESTS));

            }

        }
    }
}
