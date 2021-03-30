package com.casatamplarului.ctscanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentFormDisplayBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class FormDisplayFragment : BaseFragment(R.layout.fragment_form_display) {
    private val binding by viewBinding(FragmentFormDisplayBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpWatchers()

        viewModel.backButtonDisplay.value = true
        binding.wvLoadingPb.isVisible = true
        setUpWebView()
        setUpBtn()
    }

    private fun setUpWatchers() {
        viewModel.pageLoaded.observe(this) {
            binding.wvLoadingPb.isVisible = false
            binding.scanNextTv.isVisible = true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() = binding.web.apply {
        settings.javaScriptEnabled = true
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                viewModel.pageLoaded.call()
            }
        }

        viewModel.scannedUrl.value?.let {
            loadUrl(it)
        }
    }

    private fun setUpBtn() {
        binding.scanNextTv.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}