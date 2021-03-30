package com.casatamplarului.ctscanner.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.casatamplarului.ctscanner.viewmodel.AppViewModel

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected val viewModel: AppViewModel by activityViewModels()
}