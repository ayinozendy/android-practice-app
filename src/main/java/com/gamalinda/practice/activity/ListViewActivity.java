package com.gamalinda.practice.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringArrayRes;

@EActivity(R.layout.list_view)
public class ListViewActivity extends SherlockActivity {

    @StringArrayRes(R.array.list_things)
    String[] listOfThings;

    @ViewById(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
    }

    @AfterViews
    void afterViews() {
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfThings));
    }

    @ItemClick(resName = "list_view")
    void list(int position) {
        ListViewDetailsPageActivity_.intent(this).text(listOfThings[position]).start();
    }
}
