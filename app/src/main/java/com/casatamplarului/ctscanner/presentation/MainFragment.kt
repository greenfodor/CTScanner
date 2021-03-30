package com.casatamplarului.ctscanner.presentation

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBtn()
    }

    private fun setUpBtn() {
        binding.scanBtn.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        findNavController().navigate(R.id.action_MainFragment_to_ScanFragment)
                    }

                    override fun onPermissionDenied(respone: PermissionDeniedResponse?) {
                    }

                    override fun onPermissionRationaleShouldBeShown(request: PermissionRequest?, token: PermissionToken?) {
                    }
                }).check()
        }
    }
}