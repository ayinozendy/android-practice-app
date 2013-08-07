package com.gamalinda.practice.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

@EActivity(R.layout.main)
public class MainAppActivity extends SherlockActivity {

    @StringArrayRes
    String[] things;

    @ViewById(R.id.left_drawer)
    android.widget.ListView leftDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
    }

    @AfterViews
    void afterViews() {
        getSupportActionBar().show();
        leftDrawer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, things));
    }

}
