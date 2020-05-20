package ir.brn.driver.map

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import dagger.android.support.AndroidSupportInjection
import ir.brn.driver.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(),
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
            OnMapReadyCallback,
            GoogleMap.OnMapClickListener,
            GoogleMap.OnMyLocationButtonClickListener,
            LocationListener {

    private val REQUEST_CODE = 123
    private val REQUEST_CHECK_SETTINGS = 0
    private var mRequestingLocationUpdates: Boolean? = null


    private lateinit var currentGoogleMap: GoogleMap
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var locationRequest: LocationRequest
    private var selectedLatLng: LatLng? = null

    //region android life cycle

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleApiClient = GoogleApiClient.Builder(activity!!)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        googleApiClient.connect()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onStop() {
        super.onStop()
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
        googleApiClient.disconnect()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map.onDestroy()
    }

    //endregion

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            REQUEST_CODE -> when (resultCode) {
                Activity.RESULT_OK -> Log.e(TAG, "User agreed to make required Location settings changes.")
                Activity.RESULT_CANCELED -> {
                    Log.e(TAG, "User chose not to make required Location settings changes.")
                    mRequestingLocationUpdates = false
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        currentGoogleMap = googleMap!!
        if (selectedLatLng != null) {
            currentGoogleMap.addMarker(MarkerOptions()
                    .position(selectedLatLng as LatLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.drawable.ic_location))))
            var cameraPosition = CameraPosition.Builder()
                    .target(selectedLatLng)
                    .zoom(17f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build()
            currentGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            currentGoogleMap.isMyLocationEnabled = true

        googleMap.setOnMapClickListener(this)
        googleMap.setOnMyLocationButtonClickListener(this)
        currentGoogleMap.setOnMapClickListener(this)
        currentGoogleMap.setOnMyLocationButtonClickListener(this)

    }

    override fun onConnected(bundle: Bundle?) {
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
            if (location != null) {
                val position = CameraPosition.builder()
                        .target(LatLng(location.latitude, location.longitude))
                        .zoom(17f)
                        .bearing(0.0f)
                        .tilt(0.0f)
                        .build()
                currentGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position))
            }
            locationRequest = LocationRequest()
            locationRequest.setInterval(10000)
            locationRequest.setFastestInterval(2000)
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this)

            currentGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onMapClick(currentLatLng: LatLng?) {
        selectedLatLng = currentLatLng
        currentGoogleMap.clear()
        val l = currentGoogleMap.cameraPosition.target
        val circleDrawable = resources.getDrawable(R.drawable.ic_location)
        val markerIcon: BitmapDescriptor = getMarkerIconFromDrawable(circleDrawable)
        currentGoogleMap.addMarker(MarkerOptions()
                .position(currentLatLng!!)
                .draggable(true)
                .icon(markerIcon))

    }

    private fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor {

        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth * 1, drawable.intrinsicHeight * 1, Bitmap.Config.ARGB_8888)


        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth * 1, drawable.intrinsicHeight * 1)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }



    override fun onMyLocationButtonClick(): Boolean {
        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback {
            if (it.status.statusCode==LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                it.status.startResolutionForResult(activity!!,REQUEST_CHECK_SETTINGS)
            }
        }
        return false
    }

    override fun onLocationChanged(newLocation: Location?) {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
        var position = CameraPosition.Builder()
                .target(LatLng(newLocation!!.latitude, newLocation.longitude))
                .zoom(17f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build()

        currentGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position))
        currentGoogleMap.isTrafficEnabled = true
    }

    companion object {
        private val TAG = MapFragment::class.java.name
        private val UPDATE_INTERVAL_IN_MILLOSECONDS: Long = 1000
        private val FASTEST_UPDATE_INTERVAL_IN_MILLOSECONDS: Long = 1000
    }
}