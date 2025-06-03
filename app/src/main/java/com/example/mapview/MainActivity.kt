package com.example.mapview

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView


class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocation: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var iconDone: Drawable? = null

    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val key = "eZJlIB20kSxomNYrDijl"

        Mapbox.getInstance(this)

        val inflater = LayoutInflater.from(this)
        val rootView = inflater.inflate(R.layout.activity_main, null)
        setContentView(rootView)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocation.lastLocation.addOnSuccessListener {
            if (it != null) {
                lat = it.latitude
                lng = it.longitude

                mapView = rootView.findViewById(R.id.mapView)
                mapView.getMapAsync { map ->
                    map.setStyle("https://api.maptiler.com/maps/streets-v2/style.json?key=$key")
                    map.cameraPosition =
                        CameraPosition.Builder().target(LatLng(lat, lng)).zoom(15.0).build()
                    map.addMarker(
                        MarkerOptions()
                            .position(LatLng(lat,lng))
                            .title("Current Location")
                    )

                }

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}