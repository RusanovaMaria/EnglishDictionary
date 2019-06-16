package com.rusanova.englishdictionary.createelementactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rusanova.englishdictionary.R;
import com.rusanova.englishdictionary.list.DictionaryList;
import com.rusanova.englishdictionary.element.Dictionary;
import com.rusanova.englishdictionary.mainactivity.DataAction;

public class CreateElementActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private static final String ACTION = "action";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String TAG = "myLogs";

    private EditText mNameTextView;
    private EditText mDescriptionTextView;
    private DataAction mDataAction;

    private String name;
    private String description;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNameTextView = (EditText) findViewById(R.id.name);
        mDescriptionTextView = (EditText) findViewById(R.id.description);

        Intent intent = getIntent();
        mDataAction = (DataAction) intent.getSerializableExtra(ACTION);

        if (mDataAction == DataAction.UPDATE) {
            name = intent.getStringExtra(NAME);
            description = intent.getStringExtra(DESCRIPTION);
            id = intent.getIntExtra(ID, -1);
        } else {
            name = "";
            description = "";
            id = -1;
        }

        updateUI(mDataAction);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_dictionary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mDataAction) {
                    case INSERT:
                        addElement();
                        break;
                    case UPDATE:
                        updateElement();
                        break;
                }
            }
        });
    }

    private void updateUI(DataAction dataAction) {
        String title = "";
        String nameHint = "";
        String descriptionHint = "";
        mNameTextView.setOnEditorActionListener(this);
        mDescriptionTextView.setOnEditorActionListener(this);

        switch (dataAction) {
            case INSERT:
                title = getResources().getString(R.string.create_dictionary_title);
                nameHint = getResources().getString(R.string.dictionary_name_hint);
                descriptionHint = getResources().getString(R.string.dictionary_description_hint);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                break;
            case UPDATE:
                title = getResources().getString(R.string.update_dictionary_title);
                mNameTextView.setText(name);
                mDescriptionTextView.setText(description);
                break;
        }

        setTitle(title);
        mNameTextView.setHint(nameHint);
        mDescriptionTextView.setHint(descriptionHint);
        mNameTextView.setSelection(mNameTextView.getText().length());
    }

    private void addElement() {
        String name = mNameTextView.getText().toString();
        String description = mDescriptionTextView.getText().toString();
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
    }

    private void updateElement() {
        String name = mNameTextView.getText().toString();
        String description = mDescriptionTextView.getText().toString();
        if (!name.equals("")) {
            DictionaryList list = DictionaryList.get(this);
            Dictionary dictionary = list.getDictionary(id);
            dictionary.setName(name);
            dictionary.setDescription(description);
            list.update(dictionary);
            finish();
        } else {
            Toast toast = Toast.makeText(CreateElementActivity.this, "Please enter the name of the dictionary!",
                    Toast.LENGTH_SHORT);
            toast.show();
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
