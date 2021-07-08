package com.example.pjt114.stocka.ui.stats.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.adapter.ExpenseAdapter
import com.example.pjt114.stocka.databinding.AddExpenseDialogItemBinding
import com.example.pjt114.stocka.databinding.FragmentExpensesBinding
import com.example.pjt114.stocka.model.Expense
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.example.pjt114.stocka.util.transformIntoDatePicker
import com.example.pjt114.stocka.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*


class ExpensesFragment : Fragment() {
    private var binding: FragmentExpensesBinding? = null
    lateinit var viewModel: SharedViewModel
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = (activity as MainActivity).viewModel
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

        viewModel.getExpenses().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                binding?.emptyStateExpenseTextView?.visibility = View.GONE
            }else{
                binding?.emptyStateExpenseTextView?.visibility = View.VISIBLE
            }
            expenseAdapter.differ.submitList(it)
            expenseAdapter.notifyDataSetChanged()
        })

        val aaChartView = binding?.aaChartViewExpenses

        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(AAChartType.Column)
            .backgroundColor("#FFFFFFFF")
            .title("Expenses")
            .subtitle(getString(R.string.total_expenses_number, "10,000"))
            .categories(arrayOf("Today", "Past Week", "Past Month", "Past Year"))
            .dataLabelsEnabled(false)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Total Expense")
                        .color("#00D47B")
                        .data(arrayOf(10000, 30000, 50000, 100000)),
                )
            )

        aaChartView?.aa_drawChartWithChartModel(aaChartModel)

        binding?.addNewExpenseFAB?.setOnClickListener {
            setupExpenseDialog()
        }

        expenseAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("expenseItem", it)
            }

            findNavController().navigate(
                R.id.action_statsFragment_to_expenseDetailFragment,
                bundle
            )
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

        val customList = listOf<String>("Bills", "Payment", "Tax")
        addExpenseDialogItemBinding.spinnerCategories.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, customList)
        addExpenseDialogItemBinding.spinnerCategories.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val expenseCategory = adapterView?.getItemAtPosition(position).toString()
                    viewModel.saveExpenseCategory(expenseCategory)
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {

                }
            }

        addExpenseDialogItemBinding.addExpenseButton.setOnClickListener {

            val expenseAmount =
                addExpenseDialogItemBinding.amountOfExpenseEditText.text.toString().toDouble()
            val expenseName =
                addExpenseDialogItemBinding.nameOfExpenseEditText.text.toString().trim()
            val expenseNote =
                addExpenseDialogItemBinding.noteOfExpenseEditText.text.toString().trim()





            val newExpense = Expense(
                name = expenseName,
                amount = expenseAmount,
                tag = viewModel.expenseCategory,
                date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                note = expenseNote
            )

            viewModel.insertNewExpense(newExpense)
            Toast.makeText(context, "New Expense Added Successfully", Toast.LENGTH_SHORT).show()

            expenseList.add(newExpense)
            alertDialog.dismiss()
        }

        addExpenseDialogItemBinding.cancelButton.setOnClickListener {
            alertDialog.cancel()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}