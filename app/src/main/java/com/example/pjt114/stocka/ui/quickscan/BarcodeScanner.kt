package com.example.pjt114.stocka.ui.quickscan

import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class BarcodeScanner(fragment: Fragment) {

    private val integrator by lazy {
        IntentIntegrator.forSupportFragment(fragment)
    }

    init {
        integrator.setPrompt("Scan a product reference")
        integrator.setCameraId(0) // Use a specific camera of the device
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.captureActivity = CaptureActivity::class.java
    }

    fun startScanning() {
        integrator.initiateScan()
    }
}