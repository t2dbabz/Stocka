package com.example.pjt114.stocka.ui.loginsignup

import android.content.Context
import android.os.Bundle
import android.os.UserHandle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentAccountSetUpBinding
import com.example.pjt114.stocka.model.User
import com.example.pjt114.stocka.ui.loginsignup.FieldValidators.isStringContainNumber
import com.example.pjt114.stocka.ui.loginsignup.FieldValidators.isStringLowerAndUpperCase
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class AccountSetUpFragment : Fragment() {

    private var binding: FragmentAccountSetUpBinding? = null
    lateinit var viewModel: SharedViewModel
    var isExist = false

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
        setupListeners()
        binding?.completeSignButton?.setOnClickListener {
            hasCompleteAccountData()
        }

    }

    private fun hasCompleteAccountData(){
        if(isValidate()){
            viewModel.getUser().observe(viewLifecycleOwner, { user ->
                for( i in user.indices){
                    if(user[i].userName == binding?.usernameEditTextField?.text.toString().trim()){
                        isExist = true
                        Toast.makeText(context, "Username Already Exist Please Change Username", Toast.LENGTH_SHORT).show()
                        break
                    } else{
                        isExist = false
                        continue
                    }
                }
            })
            viewModel.saveUsername(binding?.usernameEditTextField?.text.toString().trim())
            viewModel.savePassword(binding?.confirmPasswordEditTextField?.text.toString().trim())

            val user = User(
                fullName = viewModel.fullName,
                emailAddress = viewModel.emailAddress,
                phoneNumber = viewModel.phoneNumber,
                industry = viewModel.businessIndustry,
                userName = viewModel.userName,
                password = viewModel.password
            )

            userDetails()
            viewModel.insertNewUser(user)
            Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
            signUpCompleted()
            findNavController().navigate(R.id.action_accountSetUpFragment_to_loginFragment)
        }
    }

    private fun isValidate(): Boolean =
        validateUsername() && validatePassword() && validateConfirmPassword()

    private fun setupListeners() {
        binding?.usernameEditTextField?.addTextChangedListener(TextFieldValidation(binding?.usernameEditTextField!!))
        binding?.passwordEditTextField?.addTextChangedListener(TextFieldValidation(binding?.passwordEditTextField!!))
        binding?.confirmPasswordEditTextField?.addTextChangedListener(TextFieldValidation(binding?.confirmPasswordEditTextField!!))
    }

    /**
     * field must not be empty
     */

    private fun validateUsername(): Boolean {
        if (binding?.usernameEditTextField?.text.toString().trim().isEmpty()) {
            binding?.userNameTextField?.error = "Required Field!"
            binding?.usernameEditTextField?.requestFocus()
            return false
        } else {
            binding?.userNameTextField?.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) field must not be empty
     * 2) password length must not be less than 6
     * 3) password must contain at least one digit
     * 4) password must contain atleast one upper and one lower case letter
     * 5) password must contain atleast one special character.
     */
    private fun validatePassword(): Boolean {
        if (binding?.passwordEditTextField?.text.toString().trim().isEmpty()) {
            binding?.passwordTextField?.error = "Required Field!"
            binding?.passwordEditTextField?.requestFocus()
            return false
        } else if (binding?.passwordEditTextField?.text.toString().length < 6) {
            binding?.passwordTextField?.error = "password can't be less than 6"
            binding?.passwordEditTextField?.requestFocus()
            return false
        } else if (!isStringContainNumber(binding?.passwordEditTextField?.text.toString())) {
            binding?.passwordTextField?.error = "Required at least 1 digit"
            binding?.passwordEditTextField?.requestFocus()
            return false
        } else if (!isStringLowerAndUpperCase(binding?.passwordEditTextField?.text.toString())) {
            binding?.passwordTextField?.error =
                "Password must contain upper and lower case letters"
            binding?.passwordEditTextField?.requestFocus()
            return false
        }  else {
            binding?.passwordTextField?.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) field must not be empty
     * 2) password and confirm password should be same
     */
    private fun validateConfirmPassword(): Boolean {
        when {
            binding?.confirmPasswordEditTextField?.text.toString().trim().isEmpty() -> {
                binding?.confirmPasswordTextField?.error = "Required Field!"
                binding?.confirmPasswordEditTextField?.requestFocus()
                return false
            }
            binding?.confirmPasswordEditTextField?.text.toString() != binding?.passwordEditTextField?.text.toString() -> {
                binding?.confirmPasswordTextField?.error = "Passwords don't match"
                binding?.confirmPasswordEditTextField?.requestFocus()
                return false
            }
            else -> {
                binding?.confirmPasswordTextField?.isErrorEnabled = false
            }
        }

        return true
    }

    /**
     * applying text watcher on each text field
     */
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            when (view.id) {
                R.id.username_editTextField -> {
                    validateUsername()
                }

                R.id.password_editTextField -> {
                    validatePassword()
                }
                R.id.confirmPassword_editTextField -> {
                    validateConfirmPassword()
                }
            }
        }
    }

    private fun signUpCompleted(){
        val sharedPref = requireActivity().getSharedPreferences("signUpDone", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("SignUpComplete", true)
        editor.apply()
    }

    private fun userDetails(){
        val sharedPref = requireActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("fullName", viewModel.fullName)
        editor.putString("userName", viewModel.userName)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}