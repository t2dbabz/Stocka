package com.example.pjt114.stocka.ui.stats.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentProfitLossBinding
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement


class ProfitLossFragment : Fragment() {
    lateinit var viewModel: SharedViewModel
    private var binding: FragmentProfitLossBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentProfitLossBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            setUpProfitLossChart()
            setUpSpinner()
    }

    fun setUpProfitLossChart(){

        val aaChartView = binding?.aaChartView1

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Spline)
            .backgroundColor("#FFFFFFFF")
            .title("Sales")
            .categories(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"))
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Total Sales")
                    .color("#00D47B")
                    .data(arrayOf(12.0, 6.9, 9.5, 14.5)),
                AASeriesElement()
                    .name("Total Purchases")
                    .color("#F59300")
                    .data(arrayOf(8.0, 5.8, 6.7, 11.3)),
                )
            )

        aaChartView?.aa_drawChartWithChartModel(aaChartModel)

        val aaChartView2 = binding?.aaChartView2

        val aaChartModel2 : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column)
            .backgroundColor("#FFFFFFFF")
            .title("5 years P&L Projection")
            .categories(arrayOf("2023", "2024", "2025", "2026", "2027"))
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Total Sales")
                    .color("#00D47B")
                    .data(arrayOf(12.0, 6.9, 9.5, 14.5)),
                AASeriesElement()
                    .name("Total Purchases")
                    .color("#FFCC49")
                    .data(arrayOf(8.0, 5.8, 6.7, 11.3)),

                AASeriesElement()
                    .name("Total Expenses")
                    .color("#00FFFF")
                    .data(arrayOf(8.0, 5.8, 6.7, 11.3)),

                AASeriesElement()
                    .name("Net Profit")
                    .color("#F59300")
                    .data(arrayOf(8.0, 5.8, 6.7, 11.3)),
                )
            )

        aaChartView2?.aa_drawChartWithChartModel(aaChartModel2)
    }

    private fun setUpSpinner(){
        val spinner = binding?.modifyMonthSpinner
         val customList = arrayListOf("Jan - Apr", "May - Aug", "Sep - Dec")
       spinner?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, customList)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long) {
                lifecycleScope.launchWhenStarted {
                    when(position){
                        0 ->{
                            viewModel.profitAndLossJanApr()

                        }

                        1 -> {
                            viewModel.profitAndLossMayAug()
                        }

                        2 -> {
                            viewModel.profitAndLossSepDec()
                        }

                    }
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        viewModel.modifiedMonthQuery.observe(viewLifecycleOwner, {
            println(it)
            binding?.modifyMonthTextView?.text = it
        })
    }


}