package com.example.pjt114.stocka.ui.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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