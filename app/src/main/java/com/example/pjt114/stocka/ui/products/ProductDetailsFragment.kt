package com.example.pjt114.stocka.ui.products

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductDetailsBinding
import com.example.pjt114.stocka.model.ProductItem
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import java.text.DecimalFormat


class ProductDetailsFragment : Fragment() {
    private var binding: FragmentProductDetailsBinding? = null
    private val args: ProductDetailsFragmentArgs by navArgs()
    lateinit var viewModel : SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (activity as MainActivity).viewModel
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
        binding?.quantitySoldAmountTextView?.text = args.productItem.quantitySold.toString()
        binding?.productDetailTotalTextView?.text =
            getString(R.string.product_detail_total_price, totalPriceOfProduct(args.productItem) )

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_edit ->{
                val productItem = args.productItem
                val bundle = Bundle().apply {
                    putSerializable("productItemEdit", productItem)
                }
                findNavController().navigate(R.id.action_productDetailsFragment_to_productEditFragment, bundle)
            }

            R.id.action_delete ->{
                val alertDialog = AlertDialog.Builder(requireContext())

                alertDialog.apply {

                    setTitle("Delete Product")
                    setMessage("Are you sure you want to delete the Product")
                    setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteProduct(args.productItem)
                            .run {
                                findNavController().navigateUp()
                            }
                    }
                    setNegativeButton("No") { _, _ ->

                    }

                }.create().show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun totalPriceOfProduct(productItem: ProductItem): String {
        val price = productItem.sellingPrice
        val quantity = productItem.quantity

        val total = price * quantity
        return (total).convert()
    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }

    fun Double.convert(): String {
        val format = DecimalFormat("#,###.00")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(this).toString()
    }

}