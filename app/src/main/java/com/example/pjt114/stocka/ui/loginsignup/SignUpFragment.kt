package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.continueButton?.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_accountSetUpFragment)
        }

        return fragmentBinding.root
    }

}