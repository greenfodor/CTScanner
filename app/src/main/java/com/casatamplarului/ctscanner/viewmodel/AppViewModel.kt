package com.casatamplarului.ctscanner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    val scannedUrl = MutableLiveData<String>()
}