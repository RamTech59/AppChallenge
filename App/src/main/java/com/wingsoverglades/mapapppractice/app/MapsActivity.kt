package com.wingsoverglades.mapapppractice.app

import android.support.v4.app.FragmentActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity()
{
	
	private var mMap: GoogleMap? = null // Might be null if Google Play services APK is not available.
	
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_maps)
		setUpMapIfNeeded()
	}
	
	override fun onResume()
	{
		super.onResume()
		setUpMapIfNeeded()
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
		if (mMap == null)
		{
			// Try to obtain the map from the SupportMapFragment.
			mMap = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).map
			// Check if we were successful in obtaining the map.
			if (mMap != null)
			{
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
		mMap!!.addMarker(MarkerOptions().position(LatLng(0.0,0.0)).title(""))
		mMap!!.addMarker(MarkerOptions().position(LatLng(0.0,0.0)).title(""))
	}
}
