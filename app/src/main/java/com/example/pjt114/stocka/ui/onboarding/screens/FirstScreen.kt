package com.example.pjt114.stocka.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {
    var binding : FragmentFirstScreenBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        val button = binding?.button
        button?.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return fragmentBinding.root
    }


}