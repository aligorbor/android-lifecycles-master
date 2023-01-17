package com.example.android.codelabs.lifecycle.step5

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class SeekBarViewModel : ViewModel() {
 //   @JvmField
    private var _seekbarValue = MutableLiveData<Int>()
    val seekbarValue: LiveData<Int>
    get() = _seekbarValue
    fun setSeekbarValue(value:Int){
        _seekbarValue.value = value
    }
}