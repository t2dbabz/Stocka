package com.example.pjt114.stocka.ui.products

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductDetailsBinding
import com.example.pjt114.stocka.databinding.FragmentProductEditBinding
import com.example.pjt114.stocka.model.ProductItem
import com.example.pjt114.stocka.util.getBase64
import com.example.pjt114.stocka.util.setLocalImage
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import com.github.dhaval2404.imagepicker.ImagePicker


class ProductEditFragment : Fragment(), OnImageOptionListener {
    private var binding: FragmentProductEditBinding? = null
    private val args: ProductEditFragmentArgs by navArgs()
    lateinit var viewModel : SharedViewModel
    var productType : String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (activity as MainActivity).viewModel
        val fragmentBinding = FragmentProductEditBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadProductDetails()
        setupListeners()
        setUpSpinner()

        binding?.updateProductButton?.setOnClickListener {
            saveProductDetails()
        }

    }


    private fun loadProductDetails(){
        binding?.productNameEditText?.setText(args.productItemEdit.name)
       binding?.buyingPriceEditText?.setText(args.productItemEdit.buyingPrice.toString())
        binding?.sellingPriceEditText?.setText(args.productItemEdit.sellingPrice.toString())
        binding?.productQuantityEditEditText?.setText(args.productItemEdit.quantity.toString())
        binding?.productBarcodeEditEditText?.setText(args.productItemEdit.barcode)

        val imageView =  binding?.productDetailImageImageView
        val imageByteArray = Base64.decode(args.productItemEdit.productImage, Base64.DEFAULT)
        Glide.with(imageView!!.context).load(imageByteArray).placeholder(R.drawable.add_image_placeholder).into(imageView)
    }

    fun saveProductDetails(){

        if (isValidate()){
            val productName = binding?.productNameEditText?.text.toString().trim()
            val productBuyingPrice = binding?.buyingPriceEditText?.text.toString().trim()
            val productSellingPrice = binding?.sellingPriceEditText?.text.toString().trim()
            val productQuantity = binding?.productQuantityEditEditText?.text.toString().trim()

            val updatedProduct = ProductItem(
                id = args.productItemEdit.id,
                productImage = args.productItemEdit.productImage,
                name = productName,
                quantity = productQuantity.toInt(),
                sellingPrice= productSellingPrice.toDouble(),
                buyingPrice = productBuyingPrice.toDouble(),
                productType = args.productItemEdit.productType,
                barcode = args.productItemEdit.barcode
            )
            viewModel.insertNewProduct(updatedProduct)
            Toast.makeText(requireContext(), "Product Updated successfully", Toast.LENGTH_SHORT).show()
            println(updatedProduct)
        }

        findNavController().navigate(R.id.action_productEditFragment_to_productUpdateFragment)

    }


    private fun setUpSpinner(){
        val spinner = binding?.productTypeSpinner
        val customList = arrayListOf("${args.productItemEdit.productType}","Other", "Provisions", "Kitchen")
        spinner?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, customList)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long) {
                lifecycleScope.launchWhenStarted {
                    when(position){
                        1 ->{
                            viewModel.setProductTypeOthers()

                        }

                        2 -> {

                            viewModel.setProductTypeProvisions()
                        }

                        3 -> {
                            viewModel.setProductTypeKitchen()

                        }

                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        viewModel.productType.observe(viewLifecycleOwner, {
            productType = it
        })


    }



    private fun isValidate(): Boolean =
        validateProductName() && validateSellingPrice() && validateBuyingPrice() && validateQuantity()
                && validateBarcode()


    private fun setupListeners() {
        binding?.productNameEditText?.addTextChangedListener(TextFieldValidation(binding?.productNameEditText!!))
        binding?.sellingPriceEditText?.addTextChangedListener(TextFieldValidation(binding?.sellingPriceEditText!!))
        binding?.buyingPriceEditText?.addTextChangedListener(TextFieldValidation(binding?.buyingPriceEditText!!))
        binding?.productQuantityEditEditText?.addTextChangedListener(TextFieldValidation(binding?.productQuantityEditEditText!!))
        binding?.productBarcodeEditEditText?.addTextChangedListener(TextFieldValidation(binding?.productBarcodeEditEditText!!))
    }



    private fun validateProductName(): Boolean {
        if (binding?.productNameEditText?.text.toString().trim().isEmpty()) {
            binding?.productNameTextField?.error = "Required Field!"
            binding?.productNameEditText?.requestFocus()
            return false
        } else {
            binding?.productNameTextField?.isErrorEnabled = false
        }
        return true
    }

    private fun validateBuyingPrice(): Boolean {
        if (binding?.buyingPriceEditText?.text.toString().trim().isEmpty()) {
            binding?.buyingPriceTextField?.error = "Required Field!"
            binding?.buyingPriceEditText?.requestFocus()
            return false
        } else {
            binding?.buyingPriceTextField?.isErrorEnabled = false
        }
        return true
    }

    private fun validateSellingPrice(): Boolean {
        if (binding?.sellingPriceEditText?.text.toString().trim().isEmpty()) {
            binding?.sellingPriceTextField?.error = "Required Field!"
            binding?.sellingPriceEditText?.requestFocus()
            return false
        } else {
            binding?.sellingPriceTextField?.isErrorEnabled = false
        }
        return true
    }

    private fun validateQuantity(): Boolean {
        if (binding?.productQuantityEditEditText?.text.toString().trim().isEmpty()) {
            binding?.productQuantityEditTextField?.error = "Required Field!"
            binding?.productQuantityEditEditText?.requestFocus()
            return false
        } else {
            binding?.productQuantityEditTextField?.isErrorEnabled = false
        }
        return true
    }

    private fun validateBarcode(): Boolean {
        if (binding?.productBarcodeEditEditText?.text.toString().trim().isEmpty()) {
            binding?.productBarcodeEditTextField?.error = "Required Field!"
            binding?.productBarcodeEditEditText?.requestFocus()
            return false
        } else {
            binding?.productBarcodeEditTextField?.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            when (view.id) {
                R.id.productName_editText -> {
                    validateProductName()
                }
                R.id.buyingPrice_editText -> {
                    validateBuyingPrice()
                }
                R.id.sellingPrice_editText-> {
                    validateSellingPrice()
                }
                R.id.productQuantityEdit_editText -> {
                    validateQuantity()
                }

                R.id.productBarcodeEdit_editText -> {
                    validateBarcode()
                }
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

                    viewModel.saveProductImageString(base64)
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