package com.gamalinda.practice.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.gamalinda.practice.service.RestMethod;
import com.googlecode.androidannotations.annotations.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;

@EActivity(R.layout.upload_view)
public class UploadViewActivity extends SherlockActivity {

    private static final int SELECT_PHOTO = 100;
    private static final String URL = "http://127.0.0.1:3000/api/posts";

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
        uploadImage();
    }

    @Background
    void uploadImage() {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        parts.add("post[image]", new FileSystemResource(getRealPathFromURI(imageUri)));
        parts.add("post[title]", "TITLE");
        RestMethod.uploadImage(URL, parts);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
