package com.gamalinda.practice.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.main)
public class MainAppActivity extends SherlockActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
    }
}
