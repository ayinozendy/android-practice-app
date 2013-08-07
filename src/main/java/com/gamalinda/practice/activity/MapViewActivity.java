package com.gamalinda.practice.activity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gamalinda.practice.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.map_view)
public class MapViewActivity extends SherlockFragmentActivity implements LocationListener {

    private static final float ZOOM_LEVEL = 5f;
    private static final int MAP_UPDATE_TIME_INTERVAL = 1000; //in milliseconds
    private static final int MAP_UPDATE_DISTANCE_INTERVAL = 1; //in meters
    private static final long PHIL_LAT = (long) 11.8728;
    private static final long PHIL_LONG = (long) 122.8613;

    private GoogleMap googleMap;

    private LocationManager locationManager;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().show();
    }

    @AfterViews
    void afterViews() {
        initMap();
        initLocationManager();
    }

    private void initMap() {
        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_LEVEL));

        LatLng coordinates = new LatLng(PHIL_LAT, PHIL_LONG);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
    }

    private void initLocationManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        locationManager.requestLocationUpdates(provider,
                MAP_UPDATE_TIME_INTERVAL,
                MAP_UPDATE_DISTANCE_INTERVAL,
                this);
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
