package com.example.pjt114.stocka.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {

    private var binding: FragmentProductsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


}