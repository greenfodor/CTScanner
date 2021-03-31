package com.casatamplarului.ctscanner.presentation

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.navigation.fragment.findNavController
import com.casatamplarului.ctscanner.R
import com.casatamplarului.ctscanner.databinding.FragmentMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBtn()
    }

    private fun setUpBtn() {
        binding.scanTv.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        findNavController().navigate(R.id.action_MainFragment_to_ScanFragment)
                    }

                    override fun onPermissionDenied(respone: PermissionDeniedResponse?) {
                        showPermissionPermanentlyDeniedDialog()
                    }

                    override fun onPermissionRationaleShouldBeShown(request: PermissionRequest?, token: PermissionToken?) {
                        showPermissionRequiredDialog(token)
                    }
                })
                .check()
        }
    }

    private fun showPermissionPermanentlyDeniedDialog() {
        context?.apply {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.permissions_permanently_denied_title)
                .setMessage(R.string.permissions_permanently_denied_message)
                .setPositiveButton(R.string.go_to_settings) { dialog, _ ->
                    val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    settingsIntent.data = Uri.fromParts("package", requireActivity().packageName, null)
                    startActivity(settingsIntent)

                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun showPermissionRequiredDialog(token: PermissionToken?) {
        context?.apply {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.permission_required_title)
                .setMessage(R.string.permission_required_message)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    token?.continuePermissionRequest()

                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    token?.cancelPermissionRequest()

                    dialog.dismiss()
                }
                .show()
        }
    }
}