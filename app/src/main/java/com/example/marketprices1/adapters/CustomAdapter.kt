package com.example.marketprices1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketprices1.R
import com.example.marketprices1.databinding.RvitemBinding
import com.example.marketprices1.models.Product
import com.squareup.picasso.Picasso

class CustomAdapter(
    private var modelList: ArrayList<Product>,
    private val listener: OnClicListener
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    fun  setFilteredList(filteredList:ArrayList<Product>){
        modelList=ArrayList()
        this.modelList= filteredList
        notifyDataSetChanged()
    }
    class ViewHolder(val rvitemBinding: RvitemBinding) :
        RecyclerView.ViewHolder(rvitemBinding.root) {

        fun onBind(product: Product, position: Int) {
            rvitemBinding.name.text = product.name
            Picasso.get().load(product.url).into(rvitemBinding.image)
            rvitemBinding.price.text = product.price.toString()
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

        viewHolder.onBind(modelList[position], position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = modelList.size


    interface OnClicListener {
        fun onItemClic(modelList: ArrayList<Product>, position: Int)
    }


}
