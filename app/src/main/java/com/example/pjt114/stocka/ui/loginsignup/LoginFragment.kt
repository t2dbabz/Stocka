package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentLoginBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class LoginFragment : Fragment() {
    lateinit var viewModel: SharedViewModel
    private var binding : FragmentLoginBinding? = null
    var isExist = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel

        (activity as AppCompatActivity).supportActionBar?.hide()

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding =fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        binding?.loginButton?.setOnClickListener {
            loginUser()
        }

        binding?.textViewSignUp?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }


    private fun loginUser(){
        viewModel.getUser().observe(viewLifecycleOwner, { user ->
            if(isValidate()){
                for (i in user.indices) {
                    if (user[i].userName == binding?.usernameEditTextField?.text.toString().trim()) {
                                if (user[i].password == binding?.passwordEditTextField?.text.toString().trim()) {
                                    val display = user[i].userName
                                    val bundle = bundleOf("display" to display)
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                        } else {
                            Toast.makeText(
                                context,
                                "Username or Password is Incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        isExist = true
                        break
                    } else {
                        isExist = false
                    }
                }

            }

        })
    }


    private fun setupListeners() {
        binding?.usernameEditTextField?.addTextChangedListener(TextFieldValidation(binding?.usernameEditTextField!!))
        binding?.passwordEditTextField?.addTextChangedListener(TextFieldValidation(binding?.passwordEditTextField!!))
    }


    private fun isValidate(): Boolean =
        validateUsername() && validatePassword()

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

    private fun validatePassword(): Boolean {
        if (binding?.passwordEditTextField?.text.toString().trim().isEmpty()) {
            binding?.passwordTextField?.error = "Required Field!"
            binding?.passwordEditTextField?.requestFocus()
            return false
        } else {
            binding?.passwordTextField?.isErrorEnabled = false
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
            }
        }
    }

}