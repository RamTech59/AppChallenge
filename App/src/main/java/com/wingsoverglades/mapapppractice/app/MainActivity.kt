package com.wingsoverglades.mapapppractice.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun StartMapActivity(view: View)
	{
		val switch = Intent(this, MapsActivity::class.java)
		startActivity(switch)
	}
}