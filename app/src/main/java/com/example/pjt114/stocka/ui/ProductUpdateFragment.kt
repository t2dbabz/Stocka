package com.example.pjt114.stocka.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProductUpdateBinding


class ProductUpdateFragment : Fragment() {
    private var binding: FragmentProductUpdateBinding? = null
    val args : ProductUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductUpdateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           binding?.productTextView?.text = getString(R.string.product_purpose_message, args.purpose)

        binding?.backToHomeButton?.setOnClickListener {
            findNavController().navigate(R.id.action_productUpdateFragment_to_homeFragment)
        }
    }


//TODO BINDING NULL ON DESTROY
}