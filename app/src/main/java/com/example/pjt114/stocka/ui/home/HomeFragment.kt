package com.example.pjt114.stocka.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person.fromBundle
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentHomeBinding
import com.example.pjt114.stocka.ui.products.ProductDetailsFragmentArgs
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import java.io.Serializable


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var productAdapter: ProductAdapter
    lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (activity as MainActivity).viewModel
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setTitleColor(Color.BLACK)





        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter()

        productAdapter.differ.submitList(DataSource().loadProducts())
        binding?.homeFragmentRecyclerView?.adapter = productAdapter
        binding?.homeFragmentRecyclerView?.layoutManager = LinearLayoutManager(activity)

        val user = binding?.usernameTextView
        user?.text = arguments?.getString("display")

        productAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("productItem", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_productDetailsFragment,
                bundle
            )
        }
    }

    private fun ActionBar.setTitleColor(color: Int) {
        val text = SpannableString(title ?: "")
        text.setSpan(ForegroundColorSpan(color),0,text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        title = text
    }


}