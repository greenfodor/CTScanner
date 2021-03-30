package com.casatamplarului.ctscanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentFormDisplayBinding
import com.casatamplarului.ctscanner.viewmodel.AppViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.bind

class FormDisplayFragment : Fragment(R.layout.fragment_form_display) {
    private val binding by viewBinding(FragmentFormDisplayBinding::bind)
    private val viewModel: AppViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpWatchers()

        binding.wvLoadingPb.isVisible = true
        setUpWebView()
        setUpBtn()
    }

    private fun setUpWatchers() {
        viewModel.pageLoaded.observe(this) {
            binding.wvLoadingPb.isVisible = false
            binding.scanNextBtn.isVisible = true
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
        binding.scanNextBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}