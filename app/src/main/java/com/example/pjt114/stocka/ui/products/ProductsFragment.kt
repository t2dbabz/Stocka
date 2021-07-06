package com.example.pjt114.stocka.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {

    private var binding: FragmentProductsBinding? = null
    private lateinit var productAdapter: ProductAdapter

    private var closed = false
    val add =binding?.add
    private val edit=binding?.editF
    private val settings = binding?.settingF


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter()

        productAdapter.differ.submitList(DataSource().loadProducts())
        binding?.productItemRecyclerView?.adapter = productAdapter
        binding?.productItemRecyclerView?.layoutManager = LinearLayoutManager(activity)

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
            onAddButtonClick()
        }
        //set the edit fab on click listener
        edit?.setOnClickListener {

        }
        //set the last fab on clickListener
        settings?.setOnClickListener {

        }
    }
    private fun onAddButtonClick() {
        setVisibility(closed)
        setAnimation(closed)
        closed = !closed
    }

    // A Function used to set the Animation effect
    private fun setAnimation(closed:Boolean) {
//        if(!closed){
//            edit?.startAnimation(fromBottom)
//            settings?.startAnimation(fromBottom)
//            add?.startAnimation(rotateOpen)
//        }else{
//            edit?.startAnimation(toBottom)
//            settings?.startAnimation(toBottom)
//            add?.startAnimation(rotateClose)
//        }
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