package com.casatamplarului.ctscanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentFormDisplayBinding
import com.casatamplarului.ctscanner.viewmodel.AppViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class FormDisplayFragment : Fragment(R.layout.fragment_form_display) {
    private val binding by viewBinding(FragmentFormDisplayBinding::bind)
    private val viewModel: AppViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() = binding.web.apply {
        settings.javaScriptEnabled = true
        viewModel.scannedUrl.value?.let {
            loadUrl(it)
        }
    }
}