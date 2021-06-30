package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentSignUpBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel

class SignUpFragment : Fragment() {

    lateinit var viewModel: SharedViewModel

    private var binding: FragmentSignUpBinding? = null

    var isPhoneNumberValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel

         isPhoneNumberValid = false
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.continueButton?.setOnClickListener {
            validation()
            hasCompletedSignUpData()
        }


    }

    private fun hasCompletedSignUpData() {
        setUpPhoneNumberValidation()
        if (validation() && isPhoneNumberValid) {
            viewModel.saveFullName(binding?.fullNameEditTextField?.text.toString())
            viewModel.savePhoneNumber(binding?.phoneNumberEditTextField?.text.toString())
            viewModel.saveEmailAddress(binding?.emailAddressEditTextField?.text.toString())
            viewModel.saveIndustry(binding?.industryEditTextField?.text.toString())
            findNavController().navigate(R.id.action_signUpFragment_to_accountSetUpFragment)
        }
    }


    private fun validation(): Boolean {
        if (binding?.fullNameEditTextField?.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter Full Name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding?.phoneNumberEditTextField?.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter Phone", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding?.emailAddressEditTextField?.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter Email Address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding?.industryEditTextField?.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter your Business Industry", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun setUpPhoneNumberValidation(){
        binding?.phoneNumberEditTextField?.doOnTextChanged { text, start, count, after ->
            if (text?.length!! > 0 && text.length == 11) {
                isPhoneNumberValid = true
            }
        }
    }

}




