package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.hide()

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding =fragmentBinding

        val button = binding?.loginButton
        button?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        return fragmentBinding.root
    }


}