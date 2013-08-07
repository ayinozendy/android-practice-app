package com.gamalinda.practice.activity;

import android.os.Bundle;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringRes;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@EActivity(R.layout.image_view)
public class ImageViewActivity extends SherlockActivity {

    @StringRes(R.string.android_logo_url)
    String androidLogoUrl;

    @ViewById
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
        getSupportActionBar().setTitle(R.string.show_picture);
    }

    @AfterViews
    void afterViews() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoader.getInstance().displayImage(androidLogoUrl, image, options);
    }
}
