/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.codelabs.lifecycle.step4

import android.Manifest
import com.example.android.codelabs.lifecycle.step4.BoundLocationManager.bindLocationListenerIn
import androidx.appcompat.app.AppCompatActivity
import android.location.LocationListener
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import android.os.Bundle
import com.example.android.codelabs.lifecycle.R
import androidx.core.app.ActivityCompat
import android.widget.TextView

class LocationActivity : AppCompatActivity() {
    private val mGpsListener: LocationListener = MyLocationListener()
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }

    private fun bindLocationListener() {
        bindLocationListenerIn(this, mGpsListener, applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)
        bindLocationListener()
    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val textView = findViewById<TextView>(R.id.location)
            textView.text = location.latitude.toString() + ", " + location.longitude
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {
            Toast.makeText(
                this@LocationActivity,
                "Provider enabled: $provider", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }
}