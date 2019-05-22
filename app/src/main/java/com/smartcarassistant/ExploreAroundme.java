package com.smartcarassistant;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smartcarassistant.Model.Results;
import com.smartcarassistant.Model.myPlaces;
import com.smartcarassistant.Remote.IGoogleAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreAroundme extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    private static final int MY_PERMISSION_CODE = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleAPIClient;

    public double latitude, longitude;
    private Location mLastLocation;
    private Marker mMarker;
    private LocationRequest mLocationRequest;

    IGoogleAPIService mService;

    myPlaces currentPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.activity_explore_aroundme);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            //Init Service
            mService=Common.getGoogleAPIService();

            //Request Runtime Permission
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                checkLocationPermission();
            }

            BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId())
                    {
                        case R.id.action_hospital:
                            nearbyPlace("hospital");
                            nearbyPlace("dentist");
                            nearbyPlace("doctor");
                            break;
                        case R.id.action_fuel:
                            nearbyPlace("gas_station");
                            break;
                        case R.id.action_service:
                            nearbyPlace("car_repair");
                            nearbyPlace("car_wash");
                            break;
                        case R.id.action_market:
                            nearbyPlace("supermarket");
                            break;
                        case R.id.action_restaurant:
                            nearbyPlace("restaurant");
                            nearbyPlace("cafe");
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }
        catch (Exception e){
            FileLog.e(e);
        }
    }

    private void nearbyPlace(final String placeType) {
        mMap.clear();
        String url = getURL(latitude,longitude,placeType);

        mService.getNearbyPlaces(url)
                .enqueue(new Callback<myPlaces>() {
                    @Override
                    public void onResponse(Call<myPlaces> call, Response<myPlaces> response) {

                        currentPlace=response.body();
                        if(response.isSuccessful())
                        {
                            for(int i=0;i<response.body().getResults().length;i++)
                            {
                                MarkerOptions markerOptions= new MarkerOptions();
                                Results googlePlace=response.body().getResults()[i];
                                Double lat=Double.parseDouble(googlePlace.getGeometry().getLocation().getLat());
                                Double lng=Double.parseDouble(googlePlace.getGeometry().getLocation().getLng());
                                String placeName=googlePlace.getName();
                                String vicinity=googlePlace.getVicinity();
                                LatLng latLng=new LatLng(lat,lng);
                                markerOptions.position(latLng);
                                markerOptions.title(placeName);
                                if(placeType.equals("hospital")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital));
                                }
                                else if(placeType.equals("dentist")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital));
                                }
                                else if(placeType.equals("doctor")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital));
                                }
                                else if(placeType.equals("gas_station")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fuel));
                                }
                                else if(placeType.equals("supermarket")){
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_shopping));
                                }
                                else if(placeType.equals("restaurant")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant));
                                }
                                else if(placeType.equals("cafe")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant));
                                }
                                else if(placeType.equals("car_repair")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_service));
                                }
                                else if(placeType.equals("car_wash")) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_service));
                                }
                                else{
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                }


                                markerOptions.snippet(String.valueOf(i)); //Assign index to each marker.
                                //add map marker
                                mMap.addMarker(markerOptions);
                                //move Camera

                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<myPlaces> call, Throwable t) {

                    }
                });
    }

    private String getURL(double latitude, double longitude, String placeType) {

        StringBuilder googlePlacesURL=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesURL.append("location="+latitude+","+longitude);
        googlePlacesURL.append("&radius="+10000);
        googlePlacesURL.append("&type="+placeType);
        googlePlacesURL.append("&sensor=true");
        googlePlacesURL.append("&key="+getResources().getString(R.string.browser_key));
        Log.d("getUrl",googlePlacesURL.toString());
        return googlePlacesURL.toString();
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },MY_PERMISSION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },MY_PERMISSION_CODE);
            }
            return false;
        }
        else {
            return true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MY_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if(mGoogleAPIClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //First initialize Google Play Services
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        //Event click on marker
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Common.currentResult= currentPlace.getResults()[Integer.parseInt(marker.getSnippet())];
                startActivity(new Intent(ExploreAroundme.this,ViewPlace.class));
                return true;
            }
        });
    }

    private synchronized void buildGoogleApiClient()
    {
        mGoogleAPIClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleAPIClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleAPIClient,mLocationRequest,this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleAPIClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        if(mMarker!=null) {
            mMarker.remove();
        }
        latitude=location.getLatitude();
        longitude=location.getLongitude();

        LatLng latLng=new LatLng(latitude,longitude);
        MarkerOptions markerOptions=new MarkerOptions()
                .position(latLng).title("Your Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMarker=mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        if(mGoogleAPIClient!= null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleAPIClient,this);
        }
    }
}