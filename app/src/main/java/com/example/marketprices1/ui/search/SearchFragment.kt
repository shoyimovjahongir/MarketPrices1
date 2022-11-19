package com.example.marketprices1.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marketprices1.R
import com.example.marketprices1.adapters.CustomAdapter
import com.example.marketprices1.databinding.FragmentSearchBinding
import com.example.marketprices1.models.Product

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var customAdapter2: CustomAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var modelList: ArrayList<Product>
    private lateinit var filteredList: ArrayList<Product>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        (context as AppCompatActivity).supportActionBar?.title = "Qidiruv"

        val root: View = binding.root

        LoadData()

        binding.apply {
            customAdapter2 = CustomAdapter(modelList, object : CustomAdapter.OnClicListener {
                override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                    var bundle = Bundle()
                    bundle.putSerializable("models", modelList[position])
                    findNavController().navigate(R.id.topFragment, bundle)
                }


            })

            rvSearch.adapter = customAdapter2
            search.clearFocus()

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        filterList(newText)
                    }

                    return true
                }
            })
        }
//        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }
        return root
    }

    private fun filterList(text: String) {
        filteredList = ArrayList()
        for (list in modelList) {
            if (list.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(list)
            }
        }

//        if (filteredList.isEmpty()) {
//            Toast.makeText(requireContext(), "Data not found", Toast.LENGTH_SHORT).show()
//        } else
            customAdapter2.setFilteredList(filteredList)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun LoadData() {

        modelList = ArrayList()

        modelList.add(
            Product(
                10,
                "Qulupnay",
                8000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.strawberry
            )
        )



        modelList.add(
            Product(
                11,
                "Nok",
                7000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.pear
            )
        )

        modelList.add(
            Product(
                1,
                "Kartoshka",
                4000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.potato
            )
        )
        modelList.add(
            Product(
                2,
                "Pomidor",
                5000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.tomato
            )
        )
        modelList.add(
            Product(
                3,
                "Bodring",
                7000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.cucumber
            )
        )
        modelList.add(
            Product(
                4,
                "Karam",
                3000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.cabbage
            )
        )
        modelList.add(
            Product(
                5,
                "Sabzi",
                5000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.carrot
            )
        )
        modelList.add(
            Product(
                6,
                "Olma",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.apple
            )
        )
        modelList.add(
            Product(
                7,
                "Apelsin",
                15000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.orange
            )
        )
        modelList.add(
            Product(
                8,
                "Qovun",
                20000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.melon
            )
        )
        modelList.add(
            Product(
                9,
                "Tarvuz",
                18000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.water_melon

            )
        )


    }
}