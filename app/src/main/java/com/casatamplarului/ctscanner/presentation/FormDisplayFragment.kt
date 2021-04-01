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

        binding.wvLoadingPb.isVisible = true
        processUrl()
        setUpBtn()
    }

    private fun setUpWatchers() {
        viewModel.pageLoaded.observe(this) {
            binding.wvLoadingPb.isVisible = false
            binding.scanNextTv.isVisible = true
            binding.confirmationMessageTv.isVisible = true
        }
    }

    private fun processUrl() {
        val url = viewModel.scannedUrl.value?.replace("viewform", "formResponse")

        url?.let {
            val startIndex = it.indexOf("entry.")
            var endIndex = it.indexOf("&submit")
            if (endIndex == -1) {
                endIndex = it.lastIndex
            }
            if (startIndex > -1) {
                val confirmationMessage = it.substring(startIndex, endIndex)
                val messageQuery = confirmationMessage.split("=")

                if (messageQuery.size >= 2) {
                    binding.confirmationMessageTv.text = messageQuery[1].replace("+", " ")
                }
            }
        }

        setUpWebView()
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

        viewModel.scannedUrl.value?.replace("viewform", "formResponse")?.let {
            loadUrl(it)
        }
    }

    private fun setUpBtn() {
        binding.scanNextTv.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}