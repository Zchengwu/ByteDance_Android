package com.hellofudan.chapter1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button);
        tv1 = findViewById(R.id.hellocont);

        btn1.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                tv1.setText("Wow Nice!");
                Log.d("MainActivity", "world");
        }
    }
}
