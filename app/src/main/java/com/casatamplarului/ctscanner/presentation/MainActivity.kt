package com.casatamplarului.ctscanner.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.casatamplarului.ctscanner.databinding.ActivityMainBinding
import com.casatamplarului.ctscanner.utils.viewBinding
import com.casatamplarului.ctscanner.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: AppViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setUpObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setUpObservers() {
        viewModel.backButtonDisplay.observe(this) {
            supportActionBar?.setDisplayHomeAsUpEnabled(it ?: false)
        }
    }
}