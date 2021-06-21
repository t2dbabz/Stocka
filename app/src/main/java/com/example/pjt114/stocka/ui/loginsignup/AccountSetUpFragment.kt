package com.example.pjt114.stocka.ui.loginsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pjt114.stocka.R


class AccountSetUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_account_set_up, container, false)



        return view
    }





}