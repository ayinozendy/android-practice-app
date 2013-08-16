package com.gamalinda.practice.activity;

import android.content.Intent;
import android.net.Uri;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.youtbe_view)
public class YouTubeVideoViewActivity extends SherlockActivity {

    @Click(R.id.show_video)
    void showVideo() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=r-qbDvNBg9w")));
    }
}
