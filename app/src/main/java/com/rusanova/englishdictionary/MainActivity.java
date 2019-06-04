package com.rusanova.englishdictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION = "action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarPagerAdapter pagerAdapter =
                new AppBarPagerAdapter(getSupportFragmentManager());
        final ViewPager mPager = findViewById(R.id.pager);
        mPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pageNumber = mPager.getCurrentItem();
                switch (pageNumber) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, CreateElementActivity.class);
                        intent.putExtra(ACTION, DataAction.Insert);
                        startActivity(intent);
                        break;
                    case 1:

                }
            }
        });
    }
}
