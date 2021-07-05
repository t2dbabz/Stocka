package com.example.pjt114.stocka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pjt114.stocka.databinding.AddExpenseDialogItemBinding
import com.example.pjt114.stocka.databinding.ItemExpenseLayoutBinding
import com.example.pjt114.stocka.model.Expense
import com.example.pjt114.stocka.model.ProductItem

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(val binding: ItemExpenseLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Expense>(){
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding
        = ItemExpenseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item  = differ.currentList[position]
        holder.binding.apply {
            expenseNameTextView.text = item.name
            expenseAmountTextView.text = item.amount.toString()


            holder.itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Expense) -> Unit)? = null
    fun setOnItemClickListener(listener: (Expense) -> Unit) {
        onItemClickListener = listener
    }
}