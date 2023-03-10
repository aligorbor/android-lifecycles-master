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
package com.example.android.codelabs.lifecycle.step6

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class SavedStateViewModel(private val mState: SavedStateHandle) : ViewModel() {
    // Expose an immutable LiveData
    val name: LiveData<String>
        get() = mState.getLiveData(NAME_KEY)

    fun saveNewName(newName: String?) {
        mState[NAME_KEY] = newName
    }

    companion object {
        private const val NAME_KEY = "name"
    }
}