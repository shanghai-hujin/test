package com.example.hasee.ui.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hasee.R;

import java.util.ArrayList;
import java.util.List;


public class TestXidingActivity extends AppCompatActivity {

    private RecyclerView mRecyle;
    private List<String> testList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_xiding);

        mRecyle = (RecyclerView) findViewById(R.id.rv_test);
        mRecyle.setLayoutManager(new LinearLayoutManager(this));
        for(int i = 0; i < 50 ; i++){
            testList.add(i+"");
        }
        TestAdapter testAdapter = new TestAdapter(this, testList);
        mRecyle.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();
    }
}
