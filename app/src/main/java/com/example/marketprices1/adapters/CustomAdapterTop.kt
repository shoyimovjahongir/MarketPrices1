package com.example.marketprices1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.marketprices1.R
import com.example.marketprices1.databinding.RvitemTopBinding
import com.example.marketprices1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class CustomAdapterTop(val modelList: ArrayList<Product>, var listener: OnClicListener) :
    RecyclerView.Adapter<CustomAdapterTop.ViewHolder>() {


    class ViewHolder(val rvitemTopBinding: RvitemTopBinding) :
        RecyclerView.ViewHolder(rvitemTopBinding.root) {
        fun onBind(product: Product, position: Int) {

            Picasso.get().load(product.url).into(rvitemTopBinding.image)
            rvitemTopBinding.tvName.text=product.name
            rvitemTopBinding.tvPrice.text=product.price.toString()

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item


        return ViewHolder(
            RvitemTopBinding.inflate(
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

        viewHolder.onBind(modelList[position], position)
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = modelList.size

    interface OnClicListener {
        fun onItemClic(modelList: ArrayList<Product>, position: Int)
    }

}


//class CustomAdapterTop( val modelList: List<Product>,var listener: OnClicListener) :
//    RecyclerView.Adapter<CustomAdapterTop.ViewHolder>() {
//
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        fun onBind(product: Product, position: Int) {
//
//
//
//        }
//
//        val textView: TextView
//
//        init {
//
//            // Define click listener for the ViewHolder's View.
//            textView = view.findViewById(R.id.tv)
//        }
//    }
//
//    // Create new views (invoked by the layout manager)
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        // Create a new view, which defines the UI of the list item
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.rvitem_top, viewGroup, false)
//
//
//        return ViewHolder(view)
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//
//        viewHolder.itemView.setOnClickListener {
//            listener.onItemClic(modelList,position)
//        }
//
//        viewHolder.onBind(modelList[position],position)
//        // Get element from your dataset at this position and replace the
//        // contents of the view with that element
////        viewHolder.textView.text = dataSet[position]
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = 9
//
//    interface OnClicListener {
//        fun onItemClic(modelList: List<Product>, position: Int)
//    }
//
//}


//
//class Adapter(private val context: Context,
//              private val list: ArrayList<Model>,
//              private val cellClickListener: CellClickListener
//) : RecyclerView.Adapter<Adapter.ViewHolder>() {
//
//    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val iconIV: ImageView = view.findViewById(R.id.iconIV)
//        val titleTV: TextView = view.findViewById(R.id.titleTV)
//        val subtitleTV: TextView = view.findViewById(R.id.subtitleTV)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.row_view,parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return list.count()
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = list[position]
//        holder.iconIV.setImageDrawable(ContextCompat.getDrawable(context, data.icon))
//        holder.titleTV.text = data.title
//        holder.subtitleTV.text = data.subtitle
//
//        holder.itemView.setOnClickListener {
//            cellClickListener.onCellClickListener()
//        }
//    }
//}













































