package com.wingsoverglades.mapapppractice.app

import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : FragmentActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
	private var mMap: GoogleMap? = null // Might be null if Google Play services APK is not available.
	private var mGoogleApiClient: GoogleApiClient? = null
	public var lat: Double = 0.0
	public var lng: Double = 0.0

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_maps)
		setupLocationApi()
		setUpMapIfNeeded()
	}
	override fun onConnectionFailed(connectionResult: ConnectionResult) {
	}
	override fun onConnectionSuspended(int: Int) {
	}
	protected fun setupLocationApi()
	{
		mGoogleApiClient = GoogleApiClient.Builder(this)
			.addConnectionCallbacks(this as GoogleApiClient.ConnectionCallbacks)
			.addOnConnectionFailedListener(this as GoogleApiClient.OnConnectionFailedListener)
			.addApi(LocationServices.API)
			.build()
	}

	public override fun onConnected(connectionHint: Bundle?)
	{
		var mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

		lat = mLastLocation.latitude
		lng = mLastLocation.longitude
		mMap!!.clear()
		mMap!!.isMyLocationEnabled = true
		Toast.makeText(applicationContext, "This is your phone's last known location", Toast.LENGTH_LONG).show()
}

	protected override fun onPause()
	{
		super.onPause()
		if (mGoogleApiClient!!.isConnected) {
			mGoogleApiClient?.disconnect()
		}
	}

	override fun onResume()
	{
		super.onResume()
		setUpMapIfNeeded()
	}

	override fun onStart()
	{
		super.onStart()
		if (mGoogleApiClient != null) {
			mGoogleApiClient!!.connect()
		}
	}

	/**
	 * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
	 * installed) and the map has not already been instantiated.. This will ensure that we only ever
	 * call [.setUpMap] once when [.mMap] is not null.
	 *
	 *
	 * If it isn't installed [SupportMapFragment] (and
	 * [MapView][com.google.android.gms.maps.MapView]) will show a prompt for the user to
	 * install/update the Google Play services APK on their device.
	 *
	 *
	 * A user can return to this FragmentActivity after following the prompt and correctly
	 * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
	 * have been completely destroyed during this process (it is likely that it would only be
	 * stopped or paused), [.onCreate] may not be called again so we should call this
	 * method in [.onResume] to guarantee that it will be called.
	 */
	private fun setUpMapIfNeeded()
	{
		// Do a null check to confirm that we have not already instantiated the map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).map
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap()
			}
		}
	}
	
	/**
	 * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	 * just add a marker near Africa.
	 *
	 *
	 * This should only be called once and when we are sure that [.mMap] is not null.
	 */
	private fun setUpMap()
	{
		mMap!!.mapType = GoogleMap.MAP_TYPE_HYBRID
		mMap!!.uiSettings.isZoomControlsEnabled = true
	}
}
