package com.gamalinda.practice.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.actionbarsherlock.app.SherlockActivity;
import com.gamalinda.practice.R;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

import java.io.File;

@EActivity(R.layout.api_posting_test_view)
public class ApiPostingTestActivity extends SherlockActivity {
    private static final int SELECT_PHOTO = 999;
    private static final int TAKE_PICTURE = 1000;

    private Uri imageUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    imageUri = imageReturnedIntent.getData();
                }
                break;
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                    } catch (Exception e) {
                        Log.e("Camera", e.toString());
                    }
                }
                break;
        }
    }

    @Click(R.id.open_gallery)
    void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Click(R.id.take_picture)
    void takePicture() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(), "pic_to_post.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
}
