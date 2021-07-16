package com.example.gallery

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Float>()
    private val mutableSelectedItem2 = MutableLiveData<String>()
    val selectedItem: LiveData<Float> get() = mutableSelectedItem
    val selectedItem2: LiveData<String> get() = mutableSelectedItem2

    fun selectItem(stars: Float) {
        mutableSelectedItem.value = stars
    }

    fun selectItem2(desc: String) {
        mutableSelectedItem2.value = desc
    }
}