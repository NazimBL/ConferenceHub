package com.example.dell.conferencehub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    LinearLayout layout;
    ImageView profilePicture;
    SQLiteDatabase db;
    TextView Text;
    DataBaseHelper dbHelper;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
        Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(photo!=null)UserDatabase.compressBitmapToDb(photo,db,dbHelper,UserDatabase.TABLE_NAME,UserDatabase.userColumns);
                startActivity(new Intent(PictureActivity.this,Home.class));
            }
        });

    }

    void init(){
        setContentView(R.layout.activity_picture);
        layout=(LinearLayout)findViewById(R.id.layout);
        profilePicture=(ImageView)findViewById(R.id.img);
        Text=(TextView)findViewById(R.id.pic_text);
        dbHelper=new DataBaseHelper(this);
        db=dbHelper.getWritableDatabase();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            Bundle extra = data.getExtras();
            photo = (Bitmap) extra.get("data");
            profilePicture.setImageBitmap(photo);
        }
    }
}
