package com.example.pjt114.stocka.ui.products

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentProductsBinding
import com.google.android.material.animation.AnimationUtils
import android.view.animation.Animation
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class ProductsFragment : Fragment() {

    private var binding: FragmentProductsBinding? = null
    private lateinit var productAdapter: ProductAdapter
    lateinit var viewModel: SharedViewModel

    var closed = false
    val add =binding?.add
    val edit=binding?.editF
    val settings = binding?.settingF


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter()

//        productAdapter.differ.submitList(DataSource().loadProducts())
        binding?.productItemRecyclerView?.adapter = productAdapter
        binding?.productItemRecyclerView?.layoutManager = LinearLayoutManager(activity)

        viewModel.getAllProducts().observe(viewLifecycleOwner,{

            if (it.isNotEmpty()){
                binding?.emptyStateProductTextView?.visibility = View.GONE
            }else{
                binding?.emptyStateProductTextView?.visibility = View.VISIBLE
            }
            productAdapter.differ.submitList(it)
            productAdapter.notifyDataSetChanged()
        })



        productAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("productItem", it)
            }
            findNavController().navigate(
                R.id.action_productsFragment_to_productDetailsFragment,
                bundle
            )
        }

        //set the add fab on click listener
        binding?.add?.setOnClickListener {
            OnAddButtonClick()
            findNavController().navigate(R.id.action_productsFragment_to_productEditFragment)
        }
        //set the edit fab on click listener
        edit?.setOnClickListener {

        }
        //set the last fab on clickListener
        settings?.setOnClickListener {

        }
    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }

    private fun OnAddButtonClick() {
        setVisibility(closed)
        setAnimation(closed)
        closed = !closed
    }

    // A Function used to set the Animation effect
    private fun setAnimation(closed:Boolean) {
        if(!closed){
//            edit?.startAnimation(fromBottom)
//            settings?.startAnimation(fromBottom)
//            add?.startAnimation(rotateOpen)
        }else{
//            edit?.startAnimation(toBottom)
//            settings?.startAnimation(toBottom)
//            add?.startAnimation(rotateClose)
        }
    }
    // used to set visibility to VISIBLE / INVISIBLE
    private fun setVisibility(closed:Boolean) {
        if(!closed)
        {
            edit?.visibility = View.VISIBLE
            settings?.visibility = View.VISIBLE
        }else{
            edit?.visibility = View.INVISIBLE
            settings?.visibility = View.INVISIBLE
        }
    }


}