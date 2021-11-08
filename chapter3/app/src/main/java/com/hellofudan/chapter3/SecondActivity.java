package com.hellofudan.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        db = MainActivity.dbHelper.getWritableDatabase();

        final EditText edtxt = (EditText) findViewById(R.id.edittxt);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String ctx = edtxt.getText().toString();
                Date date = new Date();// 获取当前时间
                db.execSQL("insert into Info (content, time, status) values(?, ?, ?)", new String[]{ctx, String.valueOf(date.getTime()), "0"});

                 Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
