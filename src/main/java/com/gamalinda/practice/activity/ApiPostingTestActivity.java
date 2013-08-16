package com.gamalinda.practice.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
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

@EActivity(R.layout.api_posting_test_view)
public class ApiPostingTestActivity extends SherlockActivity {
    private static final int SELECT_PHOTO = 999;
    private static final int TAKE_PICTURE = 1000;

    private static final String URL = "http://10.10.1.231:3000/api/posts";

    private static final long PHIL_LAT = (long) 11.8728;
    private static final long PHIL_LONG = (long) 122.8613;

    @ViewById(R.id.image_view)
    ImageView imageView;

    @ViewById(R.id.post)
    TextView postTextView;

    private Uri imageUri;

    private String imagePath;

    private DisplayImageOptions options;

    private LocationManager locationManager;

    private String provider;

    @AfterViews
    void afterViews() {
        options = new DisplayImageOptions.Builder()
                .build();

        initLocationManager();
    }

    private void initLocationManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
    }

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
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (imageUri != null) {
            ImageLoader.getInstance().displayImage(imageUri.toString(), imageView, options);
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
        imagePath = photo.getAbsolutePath();
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Click(R.id.post)
    void post() {
        uploadImage();
    }

    @Background
    void uploadImage() {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        Log.e("Upload", getRealPathFromURI(imageUri));
        parts.add("post[image]", new FileSystemResource(getRealPathFromURI(imageUri)));
        parts.add("post[title]", "Android Title");
        parts.add("post[description]", "Android Description");
        parts.add("post[longitude]", ""+locationManager.getLastKnownLocation(provider).getLongitude());
        parts.add("post[latitude]", ""+locationManager.getLastKnownLocation(provider).getLatitude());
        RestMethod.uploadImage(URL, parts);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);

        String realPath = null;

        try {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            realPath = cursor.getString(column_index);
        } catch (Exception e) {
            Log.e("Get Real Path Failed", "No realPath");
            realPath = imagePath;
        }

        return realPath;
    }
}
