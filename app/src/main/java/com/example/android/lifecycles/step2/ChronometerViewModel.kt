package com.example.android.lifecycles.step2

import androidx.lifecycle.ViewModel


class ChronometerViewModel : ViewModel() {
    var startTime: Long? = null
        private set

    fun setStartTime(startTime: Long) {
        this.startTime = startTime
    }
}