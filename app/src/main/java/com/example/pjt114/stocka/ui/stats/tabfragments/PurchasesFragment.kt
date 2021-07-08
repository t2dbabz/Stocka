package com.example.pjt114.stocka.ui.stats.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentPurchasesBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import java.text.SimpleDateFormat
import java.util.*


class PurchasesFragment : Fragment() {
    private var binding : FragmentPurchasesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPurchasesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTodayDate()

        val aaChartView = binding?.aaChartViewPurchases

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
                    .data(arrayOf(10000, 20000, 30000, 40000)),
            )
            )

        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
    }

    private fun getTodayDate(){
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val string = formatter.format(calendar.time)
        binding?.modifyMonthTextView?.text = getString(R.string.todays_date_text, string)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}