package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentAccountSetUpBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class AccountSetUpFragment : Fragment() {

    private var binding: FragmentAccountSetUpBinding? = null
    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentAccountSetUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.completeSignButton?.setOnClickListener {
            hasCompleteAccountData()
        }

    }

    fun hasCompleteAccountData(){
        if(passwordValidation() && validation()){
            viewModel.saveUsername(binding?.usernameEditTextField?.text.toString().trim())
            viewModel.savePassword(binding?.confirmPasswordEditTextField?.text.toString().trim())
            findNavController().navigate(R.id.action_accountSetUpFragment_to_loginFragment)
        }

    }


    fun validation():Boolean{
        if(binding?.usernameEditTextField?.text.toString().isEmpty()){
            Toast.makeText(context, "Enter your Username", Toast.LENGTH_SHORT).show()
            return false
        }

        if(binding?.passwordEditTextField?.text.toString().isEmpty()){
            Toast.makeText(context, "Enter your Password", Toast.LENGTH_SHORT).show()
            return false
        }

        if(binding?.confirmPasswordEditTextField?.text.toString().isEmpty()){
            Toast.makeText(context, "Confirm your Password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun passwordValidation():Boolean{
      val  password = binding?.passwordEditTextField?.text.toString().trim()
      val  confirmPassword = binding?.confirmPasswordEditTextField?.text.toString().trim()
        if (password != confirmPassword){
            return false
        }
        return true
    }


}