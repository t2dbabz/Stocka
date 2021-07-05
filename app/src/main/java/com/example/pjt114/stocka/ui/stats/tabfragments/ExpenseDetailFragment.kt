package com.example.pjt114.stocka.ui.stats.tabfragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pjt114.stocka.MainActivity
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.databinding.FragmentExpenseDetailBinding
import com.example.pjt114.stocka.ui.products.ProductDetailsFragmentArgs
import com.example.pjt114.stocka.viewmodel.SharedViewModel


class ExpenseDetailFragment : Fragment() {
   private var binding: FragmentExpenseDetailBinding? = null
    lateinit var viewModel: SharedViewModel
    private val args: ExpenseDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (activity as MainActivity).viewModel

        val fragmentBinding = FragmentExpenseDetailBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null) {
            Log.e("Expense", "ExpenseDetailFragment did not receive product information")
            return
        }



        binding?.expenseDetailNameTextView?.text = args.expenseItem.name
        binding?.expenseDetailAmountTextView?.text = args.expenseItem.amount.toString()
        binding?.expenseDetailTagTextView?.text = args.expenseItem.tag
        binding?.expenseNoteTextView?.text = args.expenseItem.note
        binding?.expenseDateTextView?.text = args.expenseItem.createdAtDateFormat
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.expense_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_delete ->{
                viewModel.deleteExpense(args.expenseItem)
                    .run {
                        findNavController().navigateUp()
                    }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}