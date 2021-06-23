package com.example.pjt114.stocka.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.ui.stats.tabfragments.ExpensesFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.ProfitLossFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.PurchasesFragment
import com.example.pjt114.stocka.ui.stats.tabfragments.SalesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StatsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

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

        view.findViewById<ViewPager2>(R.id.statsFragment_viewPager).adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.statsFragment_tabLayout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.statsFragment_viewPager)

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