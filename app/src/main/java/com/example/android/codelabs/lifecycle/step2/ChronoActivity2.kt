package com.example.android.codelabs.lifecycle.step2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.android.codelabs.lifecycle.R
import androidx.lifecycle.ViewModelProvider
import android.widget.Chronometer
import androidx.activity.viewModels

class ChronoActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The ViewModelStore provides a new ViewModel or one previously created.
        //val chronometerViewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)

        val chronometerViewModel: ChronometerViewModel by viewModels()

        // Get the chronometer reference
        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        if (chronometerViewModel.startTime == null) {
            // If the start date is not defined, it's a new ViewModel so set it.
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.setStartTime(startTime)
            chronometer.base = startTime
        } else {
            // Otherwise the ViewModel has been retained, set the chronometer's base to the original
            // starting time.
            chronometer.base = chronometerViewModel.startTime!!
        }
        chronometer.start()
    }
}