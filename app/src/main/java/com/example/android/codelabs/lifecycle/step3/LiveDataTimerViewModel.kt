package com.example.android.codelabs.lifecycle.step3

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import java.util.*

class LiveDataTimerViewModel : ViewModel() {
    private val mElapsedTime = MutableLiveData<Long>()
    private val mInitialTime: Long
    private val timer: Timer

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000

                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    // Will be used when step is completed
    val elapsedTime: LiveData<Long>
        get() = mElapsedTime

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        private const val ONE_SECOND = 1000
    }
}