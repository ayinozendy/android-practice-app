package com.gamalinda.practice.activity;

import android.app.Activity;
import android.os.Bundle;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.main)
public class MainAppActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
