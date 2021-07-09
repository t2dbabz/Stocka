package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentSignUpBinding
import com.example.pjt114.stocka.ui.loginsignup.FieldValidators.isValidEmail
import com.example.pjt114.stocka.viewmodel.SharedViewModel

class SignUpFragment : Fragment() {

    lateinit var viewModel: SharedViewModel

    private var binding: FragmentSignUpBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel

        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        setupIndustryAutoComplete()

        binding?.continueButton?.setOnClickListener {
            hasCompletedSignUpData()
        }
        binding?.textViewLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun hasCompletedSignUpData() {
        if (isValidate()) {
            Log.d("Sign Up", "${isValidate()}")
            viewModel.saveFullName(binding?.fullNameEditTextField?.text.toString().trim())
            viewModel.savePhoneNumber(binding?.phoneNumberEditTextField?.text.toString().trim())
            viewModel.saveEmailAddress(binding?.emailAddressEditTextField?.text.toString().trim())
           // viewModel.saveIndustry(binding?.industryEditTextField?.text.toString().trim())
            findNavController().navigate(R.id.action_signUpFragment_to_accountSetUpFragment)
        }
    }


    fun setupIndustryAutoComplete(){
        val industryList = listOf(
            "Tech", "Fashion", "Ecommerce", "Service Provider", "Art",
            "Agriculture", "Catering", "Manufacturing" )
        val industryAutoCompleteAdapter
        =   ArrayAdapter(requireContext(), R.layout.item_autocomplete_layout, industryList)
        binding?.autoCompleteIndustry?.setAdapter(industryAutoCompleteAdapter)
    }

    private fun isValidate(): Boolean =
        validateFullName() && validatePhoneNumber() && validateEmail()  && validateIndustry()

     fun setupListeners() {
        binding?.fullNameEditTextField?.addTextChangedListener(TextFieldValidation(binding?.fullNameEditTextField!!))
        binding?.emailAddressEditTextField?.addTextChangedListener(TextFieldValidation(binding?.emailAddressEditTextField!!))
        binding?.phoneNumberEditTextField?.addTextChangedListener(TextFieldValidation(binding?.phoneNumberEditTextField!!))
        binding?.autoCompleteIndustry?.addTextChangedListener(TextFieldValidation(binding?.autoCompleteIndustry!!))
    }

    /**
     * field must not be empty
     */

    private fun validateFullName(): Boolean {
        if (binding?.fullNameEditTextField?.text.toString().trim().isEmpty()) {
            binding?.fullNameTextField?.error = "Required Field!"
            binding?.fullNameEditTextField?.requestFocus()
            return false
        } else {
            binding?.fullNameTextField?.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) field must not be empty
     * 2) text should matches email address format
     */
    private fun validateEmail(): Boolean {
        if (binding?.emailAddressEditTextField?.text.toString().trim().isEmpty()) {
            binding?.emailAddressEditTextField?.error = "Required Field!"
            binding?.emailAddressEditTextField?.requestFocus()
            return false
        } else if (!isValidEmail(binding?.emailAddressEditTextField?.text.toString())) {
            binding?.emailAddressTextField?.error = "Invalid Email!"
            binding?.emailAddressEditTextField?.requestFocus()
            return false
        } else {
            binding?.emailAddressTextField?.isErrorEnabled = false
        }
        return true
    }


    private fun validatePhoneNumber(): Boolean {
        if (binding?.phoneNumberEditTextField?.text.toString().trim().isEmpty()) {
            binding?.phoneNumberTextField?.error = "Required Field!"
            binding?.phoneNumberEditTextField?.requestFocus()
            return false
        } else if (binding?.phoneNumberEditTextField?.text.toString().length < 11) {
            binding?.phoneNumberTextField?.error = "phone number can't be less than 11"
            binding?.phoneNumberEditTextField?.requestFocus()
            return false
        } else {
            binding?.phoneNumberTextField?.isErrorEnabled = false
        }
        return true
    }

    /**
     * field must not be empy
     */

    private fun validateIndustry(): Boolean {
        if (binding?.autoCompleteIndustry?.text.toString().trim().isEmpty()) {
            binding?.industryTextField?.error = "Required Field!"
            binding?.autoCompleteIndustry?.requestFocus()
            return false
        } else {
            binding?.industryTextField?.isErrorEnabled = false
        }
        return true
    }


    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            when (view.id) {
                R.id.fullName_editTextField -> {
                    validateFullName()
                }
                R.id.phoneNumber_editTextField -> {
                    validatePhoneNumber()
                }
                R.id.emailAddress_editTextField-> {
                    validateEmail()
                }
                R.id.autoCompleteIndustry -> {
                    validateIndustry()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}





