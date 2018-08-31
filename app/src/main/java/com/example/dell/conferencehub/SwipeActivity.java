package com.example.dell.conferencehub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class SwipeActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;
    SQLiteDatabase conf_db,user_db;
    ConferenceDbHelper conf_helper;
    DataBaseHelper user_helper;
    Cursor cursor;
    ArrayList list=new ArrayList();
    TextView textView;
    byte index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        //User user;
        //user=UserDatabase.findUser(UserDatabase.TABLE_NAME,UserDatabase.CONFERENCE_ID,"1",user_helper);
        UserDatabase.getUsers(UserDatabase.TABLE_NAME,UserDatabase.CONFERENCE_ID,"1",user_helper,list);

        byte i=0;
        while(i<list.size()){

            if(list.get(i)!=null)Log.d("Nazim",""+list.get(i));
            i++;
        }
        textView.setText(""+list.get(index));


    }
    void init(){

        setContentView(R.layout.activity_swipe);
        gestureDetector=new GestureDetector(this,this);
        conf_helper=new ConferenceDbHelper(this);
        conf_db=conf_helper.getWritableDatabase();
        user_helper=new DataBaseHelper(this);
        user_db=user_helper.getWritableDatabase();
        textView=(TextView)findViewById(R.id.user_id);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

//      if(v1>0)Log.d("Nazim","UP");
//      else Log.d("Nazim","Down");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(motionEvent.getY() - motionEvent1.getY() > 30){

            Log.d("Nazim","up");
            index++;
            //save to accepted
            if(list.get(index)!=null)textView.setText(""+list.get(index));
            if(index==list.size()-1)index=0;

            return true;
        }

        if(motionEvent1.getY() - motionEvent.getY() > 50){

            //list.remove(index);
            removeUser();
            index++;
            if(list.get(index)!=null)textView.setText(""+list.get(index));
            if(index==list.size()-1)index=0;

            return true;
        }
        return false;
    }
    void removeUser(){
        list.remove(index);
        byte i=index;
        while(i<list.size()){
            list.add(i,list.get(i+1));
        }
    }
}
