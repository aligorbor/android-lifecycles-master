package com.example.android.lifecycles.step1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.codelabs.lifecycle.R
import android.widget.Chronometer

class ChronoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        chronometer.start()
    }
}