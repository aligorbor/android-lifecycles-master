package com.example.android.codelabs.lifecycle.step4

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*

object BoundLocationManager {
    @JvmStatic
    fun bindLocationListenerIn(
        lifecycleOwner: LifecycleOwner,
        listener: LocationListener,
        context: Context
    ) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    internal class BoundLocationListener(
        val lifecycleOwner: LifecycleOwner,
        private val mListener: LocationListener,
        private val mContext: Context
    ) : DefaultLifecycleObserver {
        private var mLocationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            addLocationListener()
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            removeLocationListener()
        }

        fun addLocationListener() {
            // Note: Use the Fused Location Provider from Google Play Services instead.
            // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi
            mLocationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    lifecycleOwner as Activity, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    LocationActivity.REQUEST_LOCATION_PERMISSION_CODE
                )
                return
            }
            mLocationManager?.let {
                it.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    mListener
                )
                Log.d("BoundLocationMgr", "Listener added")

                // Force an update with the last location, if available.
                val lastLocation = it.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER
                )
                if (lastLocation != null) {
                    mListener.onLocationChanged(lastLocation)
                }
            }
        }

        fun removeLocationListener() =
            mLocationManager?.let {
                it.removeUpdates(mListener)
                mLocationManager = null
                Log.d("BoundLocationMgr", "Listener removed")
                null
            }


    }
}