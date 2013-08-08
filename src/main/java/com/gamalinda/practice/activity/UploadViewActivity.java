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

@EActivity(R.layout.upload_view)
public class UploadViewActivity extends SherlockActivity {

    private static final int SELECT_PHOTO = 100;

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

    @Click(R.id.upload)
    void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
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
    void image() {

    }
}
