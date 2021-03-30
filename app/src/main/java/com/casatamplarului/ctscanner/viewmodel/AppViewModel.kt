package com.casatamplarului.ctscanner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casatamplarului.ctscanner.utils.SingleLiveEvent

class AppViewModel : ViewModel() {
    val scannedUrl = MutableLiveData<String>()
    val pageLoaded = SingleLiveEvent<Any>()
}