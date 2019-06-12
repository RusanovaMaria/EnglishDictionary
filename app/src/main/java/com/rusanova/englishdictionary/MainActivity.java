package com.rusanova.englishdictionary;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String ACTION = "action";

    private SearchView searchView;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_fab);
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

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_search, menu);

            MenuItem searchItem = menu.findItem(R.id.search);
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(
            new ComponentName(this, MainActivity.class)));
            searchView.setIconifiedByDefault(false);

            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
          return false;
    }
}
