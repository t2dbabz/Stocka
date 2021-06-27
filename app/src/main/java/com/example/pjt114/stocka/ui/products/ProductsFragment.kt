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

//        productAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("productItem", it)
//            }
//            findNavController().navigate(
//                R.id.action_,
//                bundle
//            )
//        }




    }


}