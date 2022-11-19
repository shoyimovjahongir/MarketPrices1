package com.example.marketprices1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketprices1.R
import com.example.marketprices1.databinding.RvitemPartBinding
import com.example.marketprices1.models.ProductType
import com.squareup.picasso.Picasso

class PartAdapter(
    private var productTypeList: ArrayList<ProductType>,
    private val listener: OnClicListener
) :
    RecyclerView.Adapter<PartAdapter.ViewHolder>() {

    class ViewHolder(val rvitemPartBinding: RvitemPartBinding) :
        RecyclerView.ViewHolder(rvitemPartBinding.root) {


        fun onBind(productType: ProductType, position: Int) {

            rvitemPartBinding.apply {

                name.text = productType.type
                Picasso.get().load(productType.url).into(image)

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvitemPartBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            listener.onItemClic(productTypeList, position)
        }
        viewHolder.onBind(productTypeList.get(position), position)
    }

    override fun getItemCount() = productTypeList.size

    interface OnClicListener {
        fun onItemClic(productTypeList: ArrayList<ProductType>, position: Int)
    }


}
