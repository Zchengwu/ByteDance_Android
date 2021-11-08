package com.hellofudan.chapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hellofudan.chapter3.R;
import com.hellofudan.chapter3.ShowAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public static MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ShowAdapter mShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据库，创建一部分初始数据
        dbHelper = new MyDatabaseHelper(this, 4);
        db = dbHelper.getWritableDatabase();

//        String a = "a";
//        for(int i = 0; i < 10; i++){
//            Date date = new Date();// 获取当前时间
//
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            db.execSQL("insert into Info(content, time, status) values(?, ?, ?)", new String[]{a, String.valueOf(date.getTime()), "0"});
//            a += "a";
//        }

        mShowAdapter = new ShowAdapter(this, db);

        // load data from db
//        List<DataBaseContract.InfoTable> data = new ArrayList<>();
//        Cursor cursor = null;
//        try{
//            cursor = db.rawQuery("select * from Info", null);
//            while(cursor.moveToNext()) {
//                String content = cursor.getString(cursor.getColumnIndex(DataBaseContract.InfoEntry.COLUMN_CONTENT));
//                long time = cursor.getLong(cursor.getColumnIndex(DataBaseContract.InfoEntry.COLUMN_TIME));
//
//                DataBaseContract.InfoTable dataItem = new DataBaseContract.InfoTable();
//                dataItem.setContent(content);
//                dataItem.setTime(time);
//                data.add(dataItem);
//            }
//        }finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mShowAdapter);

        mShowAdapter.updateData();

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.fab);
        fbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View iew) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}
