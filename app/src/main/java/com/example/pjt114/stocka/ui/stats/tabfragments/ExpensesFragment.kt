package com.example.pjt114.stocka.ui.stats.tabfragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ExpenseAdapter
import com.example.pjt114.stocka.databinding.AddExpenseDialogItemBinding
import com.example.pjt114.stocka.databinding.FragmentExpensesBinding
import com.example.pjt114.stocka.model.Expense
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.example.pjt114.stocka.util.transformIntoDatePicker
import java.text.SimpleDateFormat
import java.util.*


class ExpensesFragment : Fragment() {
    private var binding: FragmentExpensesBinding? = null
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentExpensesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenseAdapter = ExpenseAdapter()
        binding?.expenseRecyclerView?.adapter = expenseAdapter
        binding?.expenseRecyclerView?.layoutManager = LinearLayoutManager(activity)

        val aaChartView = binding?.aaChartViewExpenses

        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(AAChartType.Column)
            .backgroundColor("#FFFFFFFF")
            .title("March - June")
            .categories(arrayOf("Today", "Past Week", "Past Month", "Last Year"))
            .dataLabelsEnabled(false)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Total Sales")
                        .color("#00D47B")
                        .data(arrayOf(12.0, 6.9, 9.5, 14.5)),
                )
            )

        aaChartView?.aa_drawChartWithChartModel(aaChartModel)

        binding?.addNewExpenseFAB?.setOnClickListener {
            setupExpenseDialog()
        }

    }

    private fun setupExpenseDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val addExpenseDialogItemBinding = AddExpenseDialogItemBinding.inflate(layoutInflater)
        builder.setView(addExpenseDialogItemBinding.root)
        val alertDialog = builder.create()
        alertDialog.show()

        addExpenseDialogItemBinding.dateOfExpenseEditText.setText(
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date())
        )

        addExpenseDialogItemBinding.dateOfExpenseEditText.transformIntoDatePicker(
            requireContext(),
            "dd/MM/yyyy",
            Date()
        )

        addExpenseDialogItemBinding.addExpenseButton.setOnClickListener {

            val expenseAmount =
                addExpenseDialogItemBinding.amountOfExpenseEditText.text.toString().toDouble()
            val expenseName =
                addExpenseDialogItemBinding.nameOfExpenseEditText.text.toString().trim()
            val expenseNote =
                addExpenseDialogItemBinding.noteOfExpenseEditText.text.toString().trim()
            var expenseCategory = ""

            addExpenseDialogItemBinding.spinnerCategories.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>?,
                        view: View,
                        posiion: Int,
                        id: Long
                    ) {
                        expenseCategory = adapterView?.getItemAtPosition(posiion) as String
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            val newExpense = Expense(
                id = 1,
                name = expenseName,
                amount = expenseAmount,
                tag = expenseCategory,
                date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                note = expenseNote
            )

            expenseList.add(newExpense)
            println(expenseList)
            expenseAdapter.differ.submitList(expenseList)
            alertDialog.dismiss()
        }

        addExpenseDialogItemBinding.cancelButton.setOnClickListener {
            alertDialog.cancel()
        }

    }
}