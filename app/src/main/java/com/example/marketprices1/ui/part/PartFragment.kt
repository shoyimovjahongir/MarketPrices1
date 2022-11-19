package com.example.marketprices1.ui.part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketprices1.R
import com.example.marketprices1.adapters.CustomAdapter
import com.example.marketprices1.adapters.PartAdapter
import com.example.marketprices1.databinding.FragmentPartBinding
import com.example.marketprices1.models.Product
import com.example.marketprices1.models.ProductType
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class PartFragment : Fragment() {

    private var _binding: FragmentPartBinding? = null
    private lateinit var customAdapter: CustomAdapter
    private lateinit var partAdapter: PartAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var modelList: ArrayList<Product>
    private lateinit var productTypeList: ArrayList<ProductType>

    private lateinit var fruitList:ArrayList<Product>
    private lateinit var vegetableList:ArrayList<Product>
    private lateinit var deviseList:ArrayList<Product>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentPartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        modelList = ArrayList()
        productTypeList = ArrayList()
        binding.apply {


            LoadData()


            customAdapter = CustomAdapter(modelList, object : CustomAdapter.OnClicListener {
                override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                    var bundle = Bundle()
                    bundle.putSerializable("models", modelList[position])
                    findNavController().navigate(R.id.topFragment, bundle)

                }


            })

            partAdapter = PartAdapter(productTypeList!!, object : PartAdapter.OnClicListener {
                override fun onItemClic(productTypeList: ArrayList<ProductType>, position: Int) {
                    var bundle = Bundle()
                    if (position==0){
                        bundle.putSerializable("fruits", fruitList)
                        findNavController().navigate(R.id.myFruitFragment, bundle)
                    }else if (position==1){
                        bundle.putSerializable("fruits", vegetableList)
                        findNavController().navigate(R.id.myVegetableFragment, bundle)
                    }else if (position==2){
                        bundle.putSerializable("fruits", modelList)
                        findNavController().navigate(R.id.myPolyFragment, bundle)
                    }else if (position==3){
                        bundle.putSerializable("fruits", deviseList)
                        findNavController().navigate(R.id.myDeviseFragment, bundle)
                    }
                }

            })
            binding.rvPart.adapter = partAdapter
        }

//        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun LoadData() {

        modelList = ArrayList()
        productTypeList = ArrayList()

        fruitList = ArrayList()
        vegetableList = ArrayList()
        deviseList=ArrayList()


        productTypeList.add(
            ProductType(
                "Mevalar",
                R.drawable.fruits
            )
        )
        productTypeList.add(
            ProductType(
                "Sabzavotlar",
                R.drawable.vegetable
            )
        )

        productTypeList.add(
            ProductType(
                "Poliz ekinlari",
                R.drawable.water_melon
            )
        )
        productTypeList.add(
            ProductType(
                "Qurilmalar",
                R.drawable.system
            )
        )


        fruitList.add(
            Product(
                6,
                "Olma",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.apple
            ))
        fruitList.add(
            Product(
                6,
                "Qulupnay",
                8000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.strawberry
            ))
        fruitList.add(
            Product(
                6,
                "Nok",
                7000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.pear
            ))
        fruitList.add(
            Product(
                6,
                "Apelsin",
                15000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.orange
            ))
        fruitList.add(
            Product(
                6,
                "Shaftoli",
                16000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.peach
            ))
        fruitList.add(
            Product(
                6,
                "Uzum",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.grape
            ))
        fruitList.add(
            Product(
                6,
                "Banan",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.banana
            ))
        fruitList.add(
            Product(
                6,
                "Kivi",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.kiwi
            ))
        fruitList.add(
            Product(
                6,
                "Xurmo",
                12000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.persimmon
            ))

        fruitList.add(
            Product(
                6,
                "Anor",
                12000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.pomegranate
            ))






        vegetableList.add(
            Product(
                6,
                "Qovun",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.melon
            ))
        vegetableList.add(
            Product(
                6,
                "Tarvuz",
                8000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.water_melon
            ))

        vegetableList.add(
            Product(
                6,
                "Qalampir",
                15000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.chilli_pepper
            ))
        vegetableList.add(
            Product(
                6,
                "Rediska",
                16000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.radish
            ))
        vegetableList.add(
            Product(
                6,
                "Sabzi",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.carrot
            ))
        vegetableList.add(
            Product(
                6,
                "Baqlajon",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.eggplant
            ))
        vegetableList.add(
            Product(
                6,
                "Piyoz",
                14000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.onion
            ))
        vegetableList.add(
            Product(
                1,
                "Kartoshka",
                4000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.potato
//                "https://media.istockphoto.com/photos/three-potatoes-picture-id157430678?k=20&m=157430678&s=612x612&w=0&h=dfYLuPYwA50ojI90hZ4jCgKZd5TGnqf24UCVBszoZIA="
            )
        )
        vegetableList.add(
            Product(
                2,
                "Pomidor",
                5000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.tomato
//                "https://us.123rf.com/450wm/andegro4ka/andegro4ka1504/andegro4ka150400013/39082965-tomato-and-slice-isolated-on-white-photo-realistic-vector-illustration.jpg?ver=6"
            )
        )
        vegetableList.add(
            Product(
                3,
                "Bodring",
                7000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.cucumber
//                "https://i.pinimg.com/736x/01/7a/d9/017ad925ae84dd2bd373077faca1ec9a.jpg"
            )
        )

        deviseList.add(
            Product(
                6,
                "Kompiyuter",
                4000000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.monitor
            ))
        deviseList.add(
            Product(
                6,
                "Smartfone",
                2900000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.smartphone
            ))
        deviseList.add(
            Product(
                6,
                "Soat",
                70000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.time
            ))
        deviseList.add(
            Product(
                6,
                "Usb fleshka",
                150000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.computer
            ))
        deviseList.add(
            Product(
                6,
                "Sichqoncha",
                60000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.mouse
            ))
        deviseList.add(
            Product(
                6,
                "Televizpr",
                3000000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.tv
            ))
        deviseList.add(
            Product(
                6,
                "Radio",
                200000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.radio
            ))
        deviseList.add(
            Product(
                6,
                "Dazmol",
                1400000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.steam_iron
            ))
        deviseList.add(
            Product(
                1,
                "Mashina",
                60000000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.car
//                "https://media.istockphoto.com/photos/three-potatoes-picture-id157430678?k=20&m=157430678&s=612x612&w=0&h=dfYLuPYwA50ojI90hZ4jCgKZd5TGnqf24UCVBszoZIA="
            )
        )
        deviseList.add(
            Product(
                2,
                "Mototsikl",
                10000000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.motorbike
//                "https://us.123rf.com/450wm/andegro4ka/andegro4ka1504/andegro4ka150400013/39082965-tomato-and-slice-isolated-on-white-photo-realistic-vector-illustration.jpg?ver=6"
            )
        )
        deviseList.add(
            Product(
                3,
                "Velosiped",
                7000000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.bicycle
//                "https://i.pinimg.com/736x/01/7a/d9/017ad925ae84dd2bd373077faca1ec9a.jpg"
            )
        )


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
                R.drawable.potato
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