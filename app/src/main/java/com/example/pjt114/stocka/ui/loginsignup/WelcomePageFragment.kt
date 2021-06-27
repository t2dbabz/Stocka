package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentWelcomePageBinding


class WelcomePageFragment : Fragment() {
    private var binding: FragmentWelcomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


}