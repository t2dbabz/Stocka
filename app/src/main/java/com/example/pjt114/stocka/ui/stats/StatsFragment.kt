package com.example.pjt114.stocka.ui.stats

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
import androidx.viewpager2.widget.ViewPager2
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentStatsBinding
import com.example.pjt114.stocka.ui.stats.tabfragments.ExpensesFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.ProfitLossFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.PurchasesFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.SalesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StatsFragment : Fragment() {
    private var binding: FragmentStatsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStatsBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        val fragmentList = arrayListOf<Fragment>(
            ProfitLossFragment(),
            SalesFragment(),
            PurchasesFragment(),
            ExpensesFragment(),
        )

        val adapter = StatsViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding?.statsFragmentViewPager?.adapter = adapter

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding?.statsFragmentTabLayout!!
        val viewPager2 = binding?.statsFragmentViewPager!!

        val statsTabListName = arrayListOf<String>(
            "Profit/Loss",
            "Sales",
            "Purchases",
            "Expenses"
        )

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = statsTabListName[position]
        }.attach()
    }

}