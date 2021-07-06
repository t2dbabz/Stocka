package com.example.pjt114.stocka.ui.quickscan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentQuickScanBinding
import com.example.pjt114.stocka.databinding.QuickScanBottomsheetLayoutBinding
import com.example.pjt114.stocka.model.ProductItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator


class QuickScanFragment : Fragment() {
    private var productList = mutableListOf<ProductItem>()
    private var count: Int = 0
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

        val startScanButton = binding?.scanButton

        //textViewResult = view.findViewById<TextView>(R.id.quickScanProductName_textView)
        textViewResult = binding?.bottomSheet?.quickScanProductNameTextView!!


        bottomSheetBehaviour = BottomSheetBehavior.from(view.findViewById<FrameLayout>(R.id.bottomSheet)).apply {
            peekHeight = 0
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        startScanButton?.setOnClickListener {
            runWithPermissions(Permission.CAMERA) {
                barcodeManager.startScanning()
                // Go to onActivityResult to get scan result.
            }
        }
        productList = DataSource().loadProducts()

        binding?.bottomSheet?.quickScanAddQtyButton?.setOnClickListener {
            count++
            binding?.bottomSheet?.quickScanSaleQtyTextView?.text = count.toString()

        }

        binding?.bottomSheet?.quickScanRemoveQtyButton?.setOnClickListener {
            if(count>0){
                count--
                binding?.bottomSheet?.quickScanSaleQtyTextView?.text = count.toString()
            }

        }

        binding?.bottomSheet?.quickScanSellButton?.setOnClickListener {
            findNavController().navigate(R.id.action_quickScanFragment_to_productUpdateFragment)
        }
    }


    private fun searchListForMatch(barcode:String){
        for( item in productList.indices ){
            if(productList[item].barcode == barcode){
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                binding?.bottomSheet?.quickScanProductNameTextView?.text = productList[item].name
                binding?.bottomSheet?.quickScanProductPriceTextView?.text =
                    getString(R.string.quickScan_price, productList[item].sellingPrice.toString())

                binding?.bottomSheet?.quickScanProductQuantityTextView?.text =
                    getString(R.string.quick_scan_qty, productList[item].quantity.toString())

               // binding?.bottomSheet?.quickScanProductImageView?.setImageResource(productList[item].productImage)
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
               searchListForMatch(barcode)
            }
        } else {
            Toast.makeText(requireContext(), "No result", Toast.LENGTH_LONG)
                .show()
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }

}