package com.example.pjt114.stocka.ui.account

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
import com.example.pjt114.stocka.R


class AccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val logout = view.findViewById<ConstraintLayout>(R.id.entry4)
        logout.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
        return view
    }


}