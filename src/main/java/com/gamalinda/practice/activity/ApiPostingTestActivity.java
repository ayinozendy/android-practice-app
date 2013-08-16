package com.gamalinda.practice.activity;

import android.content.Intent;
import android.net.Uri;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.api_posting_test_view)
public class ApiPostingTestActivity extends SherlockActivity {
    private static final int SELECT_PHOTO = 999;

    private Uri imageUri;

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

    @Click(R.id.open_gallery)
    void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
}
