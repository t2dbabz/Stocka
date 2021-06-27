package com.example.pjt114.stocka.ui.stats.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentSalesBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement


class SalesFragment : Fragment() {
    private var binding: FragmentSalesBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSalesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val aaChartView = binding?.aaChartViewSales

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Spline)
            .backgroundColor("#FFFFFFFF")
            .title("March - June")
            .categories(arrayOf("Mar", "Apr", "May", "Jun"))
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Total Sales")
                    .color("#00D47B")
                    .data(arrayOf(12.0, 6.9, 9.5, 14.5)),
                )
            )

        aaChartView?.aa_drawChartWithChartModel(aaChartModel)


    }


}