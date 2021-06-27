package com.example.pjt114.stocka.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter()

        productAdapter.differ.submitList(DataSource().loadProducts())
        binding?.homeFragmentRecyclerView?.adapter = productAdapter
        binding?.homeFragmentRecyclerView?.layoutManager = LinearLayoutManager(activity)
    }


}