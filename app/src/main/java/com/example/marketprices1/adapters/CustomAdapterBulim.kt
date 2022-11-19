package com.example.marketprices1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketprices1.databinding.RvitemBinding
import com.example.marketprices1.models.Product
import com.example.marketprices1.models.ProductType
import com.squareup.picasso.Picasso

class CustomAdapterBulim(
    private var productList: ArrayList<ProductType>,
    private var modelList: ArrayList<Product>,
    private val listener: OnClicListener
) :
    RecyclerView.Adapter<CustomAdapterBulim.ViewHolder>() {

    class ViewHolder(val rvitemBinding: RvitemBinding) :
        RecyclerView.ViewHolder(rvitemBinding.root) {

        fun onBind(product: ProductType, position: Int) {
            rvitemBinding.name.text = product.type
            Picasso.get().load(product.url).into(rvitemBinding.image)
            rvitemBinding.price.text = ""
            rvitemBinding.tv.text=""
        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RvitemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            listener.onItemClic(modelList, position)
        }

        viewHolder.onBind(productList[position], position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = productList.size


    interface OnClicListener {
        fun onItemClic(modelList: ArrayList<Product>, position: Int)
    }


}
