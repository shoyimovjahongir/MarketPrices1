package com.example.marketprices1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketprices1.R
import com.example.marketprices1.adapters.CustomAdapterFruit
import com.example.marketprices1.databinding.FragmentFruitBinding
import com.example.marketprices1.databinding.MyFragmentFruitBinding
import com.example.marketprices1.databinding.MyFragmentVegetableBinding
import com.example.marketprices1.models.Product
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "fruits"

class MyVegetableFragment : Fragment() {
    private var fruit: ArrayList<Product>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fruit = it.getSerializable(ARG_PARAM1) as ArrayList<Product>?

        }
    }

    private lateinit var binding: MyFragmentVegetableBinding
    private lateinit var customAdapterFruit: CustomAdapterFruit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyFragmentVegetableBinding.inflate(layoutInflater)
        (context as AppCompatActivity).supportActionBar?.title="Sabzavotlar"

        binding.apply {


            customAdapterFruit= CustomAdapterFruit(fruit!!,object :CustomAdapterFruit.OnClicListener{
                override fun onItemClic(modelList: List<Product>, position: Int) {
                    var bundle=Bundle()
                    bundle.putSerializable("models", fruit!![position])
                    findNavController().navigate(R.id.topFragment,bundle)
                }

            })
            rvFruit.adapter = customAdapterFruit
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(fruit: ArrayList<Product>) =
            FruitFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, fruit)
                }
            }
    }
}