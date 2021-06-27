package com.example.pjt114.stocka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.ProductItemLayoutBinding
import com.example.pjt114.stocka.model.ProductItem

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductItemViewHolder>() {

    private val productList = DataSource().loadProducts()

    inner class ProductItemViewHolder(private val binding: ProductItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){

            fun bindItem(productItem: ProductItem){
                binding.productItemNameTextView.text = productItem.name
                binding.productItemImageView.setImageResource(productItem.productImage)
                binding.productItemQuantityTextView.text = productItem.quantity.toString()
                binding.productItemSellingPriceTextView.text = productItem.sellingPrice.toString()

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val adapterLayout = ProductItemLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProductItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = productList[position]
        holder.bindItem(product)
    }

    override fun getItemCount(): Int {
        return productList.size

    }
}