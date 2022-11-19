package com.example.marketprices1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketprices1.R
import com.example.marketprices1.databinding.FragmentFruitBinding
import com.example.marketprices1.databinding.RvitemOwnerBinding
import com.example.marketprices1.models.Product
import com.squareup.picasso.Picasso

class CustomAdapterFruit(
    private val modelList: ArrayList<Product>,
    private val listener: OnClicListener
) :
    RecyclerView.Adapter<CustomAdapterFruit.ViewHolder>() {


    class ViewHolder(val binding: RvitemOwnerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: Product, position: Int) {
            binding.apply {

                Picasso.get().load(product.url).into(image)
                price.text = product.price.toString()
                name.text = product.name

            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item


        return ViewHolder(
            RvitemOwnerBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            listener.onItemClic(modelList, position)
        }
        viewHolder.onBind(modelList[position],position)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = modelList.size


    interface OnClicListener {
        fun onItemClic(modelList: List<Product>, position: Int)
    }


}
