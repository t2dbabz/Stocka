package com.example.pjt114.stocka.adapter

import android.content.res.Resources
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.data.DataSource
import com.example.pjt114.stocka.databinding.ProductItemLayoutBinding
import com.example.pjt114.stocka.model.ProductItem
import java.text.DecimalFormat

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductItemViewHolder>() {


    //private val productList = DataSource().loadProducts()

    inner class ProductItemViewHolder(private val binding: ProductItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){

            fun bindItem(productItem: ProductItem){
                binding.productItemNameTextView.text = productItem.name

               val imageView =  binding.productItemImageView
                val imageByteArray = Base64.decode(productItem.productImage, Base64.DEFAULT)
                Glide.with(imageView.context).load(imageByteArray).placeholder(R.drawable.add_image_placeholder).into(imageView)

                binding.productItemQuantityTextView.text =
                    itemView.context.getString(R.string.product_qty, productItem.quantity.toString())

                val currency=(productItem.sellingPrice.toDouble()).convert()
                binding.productItemSellingPriceTextView.text =
                    itemView.context.getString(R.string.product_Price, currency )




            }

    }

    private val differCallback = object : DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.barcode == newItem.barcode
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
          return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, differCallback)



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
        val product = differ.currentList[position]
        holder.bindItem(product)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(product) }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ProductItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ProductItem) -> Unit) {
        onItemClickListener = listener
    }

    fun Double.convert(): String {
        val format = DecimalFormat("#,###.0")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(this).toString()
    }
}