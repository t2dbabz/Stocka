package com.example.pjt114.stocka.ui.home

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
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ProductAdapter
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.FragmentHomeBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


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

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        salesOverView()
        quickAction()
        getTodayDate()


        productAdapter = ProductAdapter()
        binding?.homeFragmentRecyclerView?.adapter = productAdapter
        binding?.homeFragmentRecyclerView?.layoutManager = LinearLayoutManager(activity)

        viewModel.getAllProductByMostQtySold().observe(viewLifecycleOwner,{

       if (it.isNotEmpty()){
           binding?.emptyStateTextView?.visibility = View.GONE
       }else{
           binding?.emptyStateTextView?.visibility = View.VISIBLE
       }

            productAdapter.differ.submitList(it)
            productAdapter.notifyDataSetChanged()


        })

        val user = binding?.usernameTextView
        user?.text = getString(R.string.welcome_text, getUserDetails())


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


    private fun salesOverView(){
        viewModel.getAllProductQuantity().observe(viewLifecycleOwner, {productQuantity ->
            var initialCount = 0
            for( i in productQuantity){

                initialCount += i
                println(initialCount)
            }

            binding?.salesOverview?.stockInAmountTextView?.text = initialCount.toString()
        })

        viewModel.getAllProductQuantitySold().observe(viewLifecycleOwner, {productQuantitySold ->
            var initialCount = 0
            for( i in productQuantitySold){

                initialCount += i
            }

            binding?.salesOverview?.stockOutAmountTextView?.text = initialCount.toString()
        })

        viewModel.getTotalSales().observe(viewLifecycleOwner, { totalSales ->
            var totalSalesAMount = 0
            for(i in totalSales.indices){
                val sellingPrice = totalSales[i].sellingPrice.toInt()
                val quantitySold = totalSales[i].quantitySold

                totalSalesAMount += sellingPrice * quantitySold
            }
            val currency=(totalSalesAMount.toDouble()).convert()
            binding?.salesOverview?.totalSalesAmountTextView?.text = getString(R.string.home_total_sales, currency)
        })
    }


    fun Double.convert(): String {
        val format = DecimalFormat("#,###.00")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(this).toString()
    }

    private fun quickAction(){
        binding?.addProductCardView?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productEditFragment)
        }

        binding?.sellProductCardView?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quickScanFragment)
        }
    }

    private fun getUserDetails(): String?{
        val sharedPref = requireActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        return sharedPref.getString("fullName", "User")
    }

    fun getTodayDate(){
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
            val string = formatter.format(calendar.time)
        binding?.overViewDateTextView?.text = getString(R.string.todays_date_text, string)
    }


}