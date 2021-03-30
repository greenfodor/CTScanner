package com.casatamplarului.ctscanner.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentScanBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class ScanFragment : BaseFragment(R.layout.fragment_scan) {
    private val binding by viewBinding(FragmentScanBinding::bind)

    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpScanner()
    }

    override fun onResume() {
        super.onResume()

        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()

        super.onPause()
    }

    private fun setUpScanner() {
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, binding.scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                onCodeScanned(it.text)
            }
        }
        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }


    private fun onCodeScanned(text: String) {
        viewModel.scannedUrl.value = text
        findNavController().navigate(R.id.action_ScanFragment_to_FormDisplayFragment)
    }
}