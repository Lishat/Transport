package com.example.subhadeep.transport;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap myGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isGoogleServiceAvailable())
        {
            setContentView(R.layout.activity_main);
            initialiseMap();
        }
        else
        {
            setContentView(R.layout.error_main);
        }
    }

    public void initialiseMap()
    {
        MapFragment myFragment = (MapFragment)(getFragmentManager().findFragmentById(R.id.mapFragment));
        myFragment.getMapAsync(this);
    }
    public boolean isGoogleServiceAvailable()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(api.isUserResolvableError(isAvailable))
        {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "Can't connect to Play Services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        myGoogleMap = googleMap;
    }
}
