package com.rusanova.englishdictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.rusanova.englishdictionary.list.DictionaryList;
import com.rusanova.englishdictionary.list.WordList;
import com.rusanova.englishdictionary.element.Dictionary;
import com.rusanova.englishdictionary.element.Word;

public class CreateElementActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private static final String TAG = "myLogs";
    private TextView mNameTextView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final int currentPageNumber = intent.getIntExtra(MainActivity.PAGE_NUMBER, -1);
        if (currentPageNumber != -1) {
            updateUI(currentPageNumber);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_dictionary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPageNumber != -1) {
                    addElement(currentPageNumber);
                }
            }
        });
    }

    private void updateUI(int pageNumber) {
        String title = "";
        String nameHint = "";
        String descriptionHint = "";
        TextView name = (TextView) findViewById(R.id.name);
        name.setOnEditorActionListener(this);
        TextView description = (TextView) findViewById(R.id.description);
        description.setOnEditorActionListener(this);

        switch (pageNumber) {
            case 0:
                title = getResources().getString(R.string.create_dictionary_title);
                nameHint = descriptionHint = getResources().getString(R.string.dictionary_name_hint);
                descriptionHint = getResources().getString(R.string.dictionary_description_hint);
                break;
            case 1:
                title = getResources().getString(R.string.create_word_title);
                nameHint = getResources().getString(R.string.word_name_hint);
                descriptionHint = getResources().getString(R.string.word_description_hint);
        }

        setTitle(title);
        name.setHint(nameHint);
        description.setHint(descriptionHint);
    }

    private void addElement(int pageNumber) {
        mNameTextView = (TextView) findViewById(R.id.name);
        String name = mNameTextView.getText().toString();
        mDescriptionTextView = findViewById(R.id.description);
        String description = mDescriptionTextView.getText().toString();
        switch (pageNumber) {
            case 0:
                if (!name.equals("")) {
                    Dictionary dictionary = new Dictionary(name, description);
                    DictionaryList list = DictionaryList.get(this);
                    list.add(dictionary);
                    finish();
                } else {
                    Toast toast = Toast.makeText(CreateElementActivity.this, "Please enter the name of the dictionary!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case 1:
                if (!name.equals("")) {
                    Word word = new Word(name, description);
                    WordList list = WordList.get(this);
                    list.add(word);
                    finish();
                } else {
                    Toast toast = Toast.makeText(CreateElementActivity.this, "Please enter the word!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        } catch (NullPointerException ex) {
            Log.d(TAG, "NullPointerException onEditorAction CreateElementActivity.class");
            return false;
        }

    }
}
