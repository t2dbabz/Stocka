package com.example.pjt114.stocka.ui.quickscan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentQuickScanBinding
import com.example.pjt114.stocka.databinding.QuickScanBottomsheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.zxing.integration.android.IntentIntegrator


class QuickScanFragment : Fragment() {

    private var binding : FragmentQuickScanBinding? = null
    private lateinit var textViewResult: TextView
    private val barcodeManager by lazy { BarcodeScanner(this) }
    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<FrameLayout>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentQuickScanBinding.inflate(inflater,container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startScanButton = view.findViewById<Button>(R.id.scan_button)

        textViewResult = view.findViewById<TextView>(R.id.quickScanProductName_textView)

        bottomSheetBehaviour = BottomSheetBehavior.from(view.findViewById<FrameLayout>(R.id.bottomSheet)).apply {
            peekHeight = 0
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        startScanButton.setOnClickListener {
            runWithPermissions(Permission.CAMERA) {
                barcodeManager.startScanning()
                // Go to onActivityResult to get scan result.
            }
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Scanning is cancelled", Toast.LENGTH_LONG).show()
            } else {
                val barcode: String = result.contents
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                textViewResult.text = barcode

            }
        } else {
            Toast.makeText(requireContext(), "No result", Toast.LENGTH_LONG)
                .show()
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}