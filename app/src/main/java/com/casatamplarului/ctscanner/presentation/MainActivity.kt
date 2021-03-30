package com.casatamplarului.ctscanner.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.casatamplarului.ctscanner.databinding.ActivityMainBinding
import com.casatamplarului.ctscanner.utils.viewBinding


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}