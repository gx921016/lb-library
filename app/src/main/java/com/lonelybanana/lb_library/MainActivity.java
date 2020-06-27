package com.lonelybanana.lb_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lonelybanana.lb_library.demo.LBLogDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        if (view.getId() ==R.id.aa){
            Intent intent = new Intent(MainActivity.this,LBLogDemoActivity.class);
            startActivity(intent);
        }
    }
}
