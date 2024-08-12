package com.bemo.panoramax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;
import technolifestyle.com.imageslider.FlipperLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bemo.panoramax.views.MainActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),HomePano.class));
                finish();
            }
        },5000);
    }
}
