package com.rusanova.englishdictionary.mainactivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rusanova.englishdictionary.createelementactivity.CreateElementActivity;
import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.element.Element;
import com.rusanova.englishdictionary.pagefragment.PageFragmentFactory;
import com.rusanova.englishdictionary.search.Search;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION = "action";

    private ViewPager mPager;
    private FragmentPagerAdapter pagerAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new AppBarPagerAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.pager);
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
                        intent.putExtra(ACTION, DataAction.INSERT);
                        startActivity(intent);
                        break;
                    case 1:

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search.execute(getApplicationContext(), searchView, mPager);
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                int pageNumber = mPager.getCurrentItem();
                List<Element> elements = PageFragmentFactory.getElements(pageNumber, getApplicationContext());
                PageFragmentFactory.form(mPager.getCurrentItem(), elements);
                return true;
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, MainActivity.class)));
        searchView.setIconifiedByDefault(false);
        return super.onCreateOptionsMenu(menu);
    }
}
