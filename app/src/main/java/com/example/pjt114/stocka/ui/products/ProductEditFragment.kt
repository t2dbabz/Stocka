package com.example.pjt114.stocka.ui.products

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductEditBinding
import com.example.pjt114.stocka.util.getBase64
import com.example.pjt114.stocka.util.setLocalImage
import com.github.dhaval2404.imagepicker.ImagePicker


class ProductEditFragment : Fragment(), OnImageOptionListener {
    private var binding: FragmentProductEditBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductEditBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.productDetailImageImageView?.setOnClickListener {
            //To get the gallery and camera option from user
            val bottomSheetFragment =
                ImageOptionBottomSheet.newInstance(this)
            bottomSheetFragment.isCancelable = false
            activity?.supportFragmentManager?.let {
                bottomSheetFragment.show(
                    it,
                    bottomSheetFragment.tag
                )
            }
        }
    }




    private fun pickImage(camera: Boolean) {
        val imagePicker = ImagePicker.with(this)
        if (camera) {
            imagePicker.cameraOnly()
        } else {
            imagePicker.galleryOnly()
        }
        imagePicker.cropSquare()
        imagePicker.compress(1000)

        imagePicker.start { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {
                val uri: Uri = data?.data!!
        binding?.productDetailImageImageView?.setLocalImage(uri)
                ImagePicker.getFilePath(data)?.let { filePath ->
                    val base64 = getBase64(filePath)


//                    val wearData = WearData(image = base64, type = Constant.TOP_WEAR)
//                    wearViewModel?.insertWearData(wearData)
                }
            }
        }
    }



    override fun onImageOptionSelected(option: String) {
        when (option) {
            ImageOptionBottomSheet.CAMERA -> {
                pickImage(true)
            }
            ImageOptionBottomSheet.GALLERY -> {
                pickImage(false)
            }
        }

    }


}