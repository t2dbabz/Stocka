package com.example.pjt114.stocka.ui.products

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductDetailsBinding
import com.example.pjt114.stocka.model.ProductItem


class ProductDetailsFragment : Fragment() {
    private var binding: FragmentProductDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null) {
            Log.e("Product", "ProductDetailsFragment did not receive product information")
            return
        }
        val args = ProductDetailsFragmentArgs.fromBundle(bundle)

        val imageView =  binding?.productDetailImageImageView
        val imageByteArray = Base64.decode(args.productItem.productImage, Base64.DEFAULT)
        Glide.with(imageView!!.context).load(imageByteArray).placeholder(R.drawable.add_image_placeholder).into(imageView)

        binding?.productDetailNameTextView?.text = args.productItem.name
        binding?.productDetailPriceTextView?.text =
            getString(R.string.product_detail_price, args.productItem.sellingPrice.toString() )

        binding?.productDetailQuantityTextVIew?.text = args.productItem.quantity.toString()
        binding?.productDetailTotalTextView?.text =
            getString(R.string.product_detail_total_price, totalPriceOfProduct(args.productItem) )

    }

    fun totalPriceOfProduct(productItem: ProductItem):String{
        val price = productItem.sellingPrice
        val quantity = productItem.quantity

        val total = price * quantity
        return  total.toString()
    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }







}