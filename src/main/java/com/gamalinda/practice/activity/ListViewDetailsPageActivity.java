package com.gamalinda.practice.activity;

import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.list_view_detail_page)
public class ListViewDetailsPageActivity extends SherlockActivity {

    @ViewById(R.id.text)
    TextView textView;

    @Extra
    String text;

    @AfterViews
    void afterViews() {
        textView.setText(text);
    }
}
