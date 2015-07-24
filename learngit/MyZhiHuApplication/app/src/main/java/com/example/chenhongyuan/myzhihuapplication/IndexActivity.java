package com.example.chenhongyuan.myzhihuapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

/**
 * Created by chenhongyuan on 15/7/21.
 */
public class IndexActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(IndexActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);


    }
}
