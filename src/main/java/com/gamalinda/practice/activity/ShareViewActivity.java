package com.gamalinda.practice.activity;

import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@EActivity(R.layout.share_view)
public class ShareViewActivity extends SherlockActivity {

    private static final int SELECT_PHOTO = 100;
    private static final String TEST_LINK = "http://developer.android.com/index.html";

    @ViewById(R.id.image)
    ImageView imageView;

    private Uri imageUri;

    private DisplayImageOptions options;

    @AfterViews
    void afterViews() {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    imageUri = imageReturnedIntent.getData();
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (imageUri != null) {
            ImageLoader.getInstance().displayImage(imageUri.toString(), imageView, options);
        }
    }

    @Click
    void gallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Click
    void link() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, TEST_LINK);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Click
    void image() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Title");
        sendIntent.setType("image/*");
        startActivity(Intent.createChooser(sendIntent, "Share image to"));
    }
}
