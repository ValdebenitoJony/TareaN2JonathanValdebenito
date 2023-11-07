package com.example.tarean2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng SedeArica = new LatLng(-18.483131197524948, -70.3103324888695);
        mMap.addMarker(new MarkerOptions().position(SedeArica).title("La Bodegaza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SedeArica));
    }


}