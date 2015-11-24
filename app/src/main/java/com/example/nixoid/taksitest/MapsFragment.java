package com.example.nixoid.taksitest;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_map)
public class MapsFragment extends Fragment {

    private String name = "name";
    private String lat = "lat";
    private String lon = "lon";
    private String desc = "desc";

    @ViewById(R.id.frame_container)
    View container;

    @ViewById(R.id.textClick)
    TextView textClick;

    @ViewById(R.id.linearLayout)
    LinearLayout linearLayout;

    @ViewById(R.id.searchImg)
    ImageView imageView;

    private GoogleMap mMap;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = new Bundle();
        bundle = getArguments();
        if ( bundle != null) {
            textClick.setText(bundle.getString(desc));
            goToLocation(bundle.getDouble(lat), bundle.getDouble(lon), bundle.getString(name));
        }

        textClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new SearchFragment_()).commit();
            }
        });
        setUpMapIfNeeded();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("work?", "onResume created");
    }

    public void goToLocation(double lon, double lat, String desc) {
                LatLng latlNg = new LatLng(lon, lat);
                 mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
                mMap.addMarker(new MarkerOptions().position(latlNg).title(desc));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlNg, 16));
            }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        }
    }

    public void getMyLocation() {
        mMap.setMyLocationEnabled(true);
        Location myLocation = mMap.getMyLocation();
        if(myLocation != null) {
            double dLatitude = myLocation.getLatitude();
            double dLongitude= myLocation.getLongitude();
            Log.d("lat", String.valueOf(dLatitude));
            Log.d("lon", String.valueOf(dLongitude));
            LatLng latlNg = new LatLng(dLatitude, dLongitude);
            mMap.addMarker(new MarkerOptions().position(latlNg).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlNg));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
        }
    }
}

