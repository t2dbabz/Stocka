package com.example.pjt114.stocka.ui.products

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
import com.example.pjt114.stocka.databinding.FragmentProductsBinding
import com.google.android.material.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.model.ProductItem
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import com.google.android.material.chip.Chip


class ProductsFragment : Fragment() {

    private var binding: FragmentProductsBinding? = null
    private lateinit var productAdapter: ProductAdapter
    lateinit var viewModel: SharedViewModel
    var productList = mutableListOf<ProductItem>()

    var closed = false
    val add =binding?.add
    val edit=binding?.editF
    val settings = binding?.settingF


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProductsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter()

        binding?.productItemRecyclerView?.adapter = productAdapter
        binding?.productItemRecyclerView?.layoutManager = LinearLayoutManager(activity)
        getAllProduct()
        setupProductType()
        setupSearchView()



        productAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("productItem", it)
            }
            findNavController().navigate(
                R.id.action_productsFragment_to_productDetailsFragment,
                bundle
            )
        }

        //set the add fab on click listener
        binding?.add?.setOnClickListener {
            OnAddButtonClick()
            findNavController().navigate(R.id.action_productsFragment_to_productEditFragment)
        }
        //set the edit fab on click listener
        edit?.setOnClickListener {

        }
        //set the last fab on clickListener
        settings?.setOnClickListener {

        }




    }

    private fun OnAddButtonClick() {
        setVisibility(closed)
        setAnimation(closed)
        closed = !closed
    }

    // A Function used to set the Animation effect
    private fun setAnimation(closed:Boolean) {
        if(!closed){
//            edit?.startAnimation(fromBottom)
//            settings?.startAnimation(fromBottom)
//            add?.startAnimation(rotateOpen)
        }else{
//            edit?.startAnimation(toBottom)
//            settings?.startAnimation(toBottom)
//            add?.startAnimation(rotateClose)
        }
    }
    // used to set visibility to VISIBLE / INVISIBLE
    private fun setVisibility(closed:Boolean) {
        if(!closed)
        {
            edit?.visibility = View.VISIBLE
            settings?.visibility = View.VISIBLE
        }else{
            edit?.visibility = View.INVISIBLE
            settings?.visibility = View.INVISIBLE
        }
    }

    private fun getAllProduct(){
        viewModel.getAllProducts().observe(viewLifecycleOwner,{

            if (it.isNotEmpty()){
                binding?.emptyStateProductTextView?.visibility = View.GONE
            }else{
                binding?.emptyStateProductTextView?.visibility = View.VISIBLE
            }
            productAdapter.differ.submitList(it)
            productAdapter.notifyDataSetChanged()

        })
    }
    private fun setupProductType(){
        binding?.chipGroup?.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId) {

                R.id.allProducts_chip -> {
                    getAllProduct()
                }

                R.id.provisions_chip -> {
                    viewModel.getAllProductType("Provisions").observe(viewLifecycleOwner,{
                        if (it.isNotEmpty()){
                            binding?.emptyStateProductTextView?.visibility = View.GONE
                        }else{
                            binding?.emptyStateProductTextView?.text = "No Products in Provisions Category"
                            binding?.emptyStateProductTextView?.visibility = View.VISIBLE
                        }
                        productAdapter.differ.submitList(it)
                        productAdapter.notifyDataSetChanged()
                    })
                }

                R.id.kitchen_chip -> {
                    viewModel.getAllProductType("Kitchen").observe(viewLifecycleOwner,{
                        if (it.isNotEmpty()){
                            binding?.emptyStateProductTextView?.visibility = View.GONE
                        }else{
                            binding?.emptyStateProductTextView?.text = "No Products in Kitchen Category"
                            binding?.emptyStateProductTextView?.visibility = View.VISIBLE
                        }

                        productAdapter.differ.submitList(it)
                        productAdapter.notifyDataSetChanged()
                    })

                }

                R.id.others_chip -> {
                    viewModel.getAllProductType("Others").observe(viewLifecycleOwner,{
                        if (it.isNotEmpty()){
                            binding?.emptyStateProductTextView?.visibility = View.GONE
                        }else{
                            binding?.emptyStateProductTextView?.text = "No Products in Others Category"
                            binding?.emptyStateProductTextView?.visibility = View.VISIBLE
                        }
                        productAdapter.differ.submitList(it)
                        productAdapter.notifyDataSetChanged()
                    })
                }
            }
        }

    }

    fun setupSearchView(){
        binding?.productSearchView?.isSubmitButtonEnabled = true
        binding?.productSearchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               if (query != null){
                   searchDatabase(query)
               }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null){
                    searchDatabase(query)
                }
                return true
            }

        })
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner, {list ->
            list.let {
                productAdapter.differ.submitList(it)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}