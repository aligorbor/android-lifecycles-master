package com.example.android.codelabs.lifecycle.step2

import androidx.lifecycle.ViewModel


class ChronometerViewModel : ViewModel() {
    var startTime: Long? = null
        private set

    fun setStartTime(startTime: Long) {
        this.startTime = startTime
    }
}