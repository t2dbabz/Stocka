package com.example.pjt114.stocka.ui.account

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.ConstraintWidget
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentAccountBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null
    lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (activity as MainActivity).viewModel
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setTitleColor(Color.BLACK)


        // Inflate the layout for this fragment
        val fragmentBinding = FragmentAccountBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.entry4?.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }

        val profile =binding?.profileName
        profile?.text = getUserDetails()
        if(profile?.text != null){
            binding?.verified?.visibility = View.VISIBLE
        }
    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }

    private fun getUserDetails(): String?{
        val sharedPref = requireActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        return sharedPref.getString("userName", "Username")
    }


}