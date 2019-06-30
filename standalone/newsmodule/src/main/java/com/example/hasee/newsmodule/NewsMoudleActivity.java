package com.example.hasee.newsmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hasee.news.NewsMainActivity;

public class NewsMoudleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_module);
        Intent intent = new Intent();
        intent.setClass(this,NewsMainActivity.class);
        startActivity(intent);
    }
}
