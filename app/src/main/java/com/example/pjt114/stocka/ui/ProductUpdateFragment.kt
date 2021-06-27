package com.example.pjt114.stocka.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductUpdateBinding


class ProductUpdateFragment : Fragment() {
    private var binding: FragmentProductUpdateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductUpdateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.backToHomeButton?.setOnClickListener {
            findNavController().navigate(R.id.action_productUpdateFragment_to_homeFragment)
        }
    }


}