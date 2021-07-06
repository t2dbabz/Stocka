package com.example.pjt114.stocka.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pjt114.stocka.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface OnImageOptionListener {
    fun onImageOptionSelected(option: String)
}


class ImageOptionBottomSheet() : BottomSheetDialogFragment(), View.OnClickListener {

    private var fragmentView: View? = null
    private var onImageOptionListener: OnImageOptionListener? = null

    companion object {
        fun newInstance(onImageOptionListener: OnImageOptionListener?): ImageOptionBottomSheet =
            ImageOptionBottomSheet().apply {
                this.onImageOptionListener = onImageOptionListener
            }
        const val TAG = "ImageOptionBottomSheet"
        const val GALLERY = "gallery"
        const val CAMERA = "camera"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet_image_options, container, false)
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal: View? = d.findViewById(R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheetInternal!!).state =
                BottomSheetBehavior.STATE_EXPANDED
        }

        return fragmentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        view?.findViewById<ImageView>(R.id.imageViewClose)?.setOnClickListener(this)
        view?.findViewById<TextView>(R.id.textViewGallery)?.setOnClickListener(this)
        view?.findViewById<TextView>(R.id.textViewCamera)?.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageViewClose -> {
                dismiss()
            }
            R.id.textViewGallery -> {
                dismiss()
                onImageOptionListener?.onImageOptionSelected(GALLERY)
            }

            R.id.textViewCamera -> {
                dismiss()
                onImageOptionListener?.onImageOptionSelected(CAMERA)
            }
        }
    }
}