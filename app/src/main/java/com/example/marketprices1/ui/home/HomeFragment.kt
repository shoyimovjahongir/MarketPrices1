package com.example.marketprices1.ui.home

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketprices1.R
import com.example.marketprices1.adapters.CustomAdapter
import com.example.marketprices1.adapters.CustomAdapterBulim
import com.example.marketprices1.adapters.CustomAdapterTop
import com.example.marketprices1.databinding.FragmentHomeBinding
import com.example.marketprices1.models.Product
import com.example.marketprices1.models.ProductType
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.squareup.picasso.Picasso
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //    private lateinit var rvAdapter: CustomAdapter
    private lateinit var rvAdapter: CustomAdapterBulim
    private lateinit var rvAdapterTop: CustomAdapterTop
    private lateinit var customAdapter: CustomAdapter
    private lateinit var customAdapter2: CustomAdapter
    private lateinit var customAdapter3: CustomAdapter

    private lateinit var modelList: ArrayList<Product>
    private lateinit var typelList: ArrayList<ProductType>

    private lateinit var fruitList:ArrayList<Product>
    private lateinit var vegetableList:ArrayList<Product>
    private lateinit var polyList:ArrayList<Product>
    private lateinit var foodList:ArrayList<Product>
    private lateinit var deviseList:ArrayList<Product>

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        LoadData()


           val linearLayoutManager = ZoomRecyclerLayout(requireContext())
           linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
           linearLayoutManager.reverseLayout = true
           linearLayoutManager.stackFromEnd = true
           binding.rv.layoutManager =
               linearLayoutManager // Add your recycler view to this ZoomRecycler layout

           val linearLayoutManager2 = ZoomRecyclerLayout(requireContext())
           linearLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL
           linearLayoutManager2.reverseLayout = true
           linearLayoutManager2.stackFromEnd = true
           binding.rv2.layoutManager = linearLayoutManager2

        val linearLayoutManager3 = ZoomRecyclerLayout(requireContext())
        linearLayoutManager3.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager3.reverseLayout = true
        linearLayoutManager3.stackFromEnd = true
        binding.rv3.layoutManager = linearLayoutManager3

//        Picasso.get().load("https://www.diagnosisdiet.com/assets/images/c/fruit-og-d176ef00.jpg").into(binding.img2)
//        Picasso.get().load("https://thumbs.dreamstime.com/b/set-devices-product-screens-media-laptop-tablet-mobile-future-devises-flat-design-eps-156719681.jpg").into(binding.img2)
        Picasso.get().load("https://thumbs.dreamstime.com/b/set-realistic-electronic-devises-dna-logotype-computer-monitor-laptop-smartphone-tablet-82918825.jpg").into(binding.img2)
        Picasso.get().load("https://image.shutterstock.com/image-vector/global-market-logo-600w-580253467.jpg").into(binding.img1)



        rvAdapter =
            CustomAdapterBulim(typelList, modelList, object : CustomAdapterBulim.OnClicListener {
                override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
//                var bundle = Bundle()
//                bundle.putSerializable("models", modelList[position])
//                findNavController().navigate(R.id.topFragment, bundle)
                var bundle = Bundle()
                bundle.putSerializable("types", typelList)
                findNavController().navigate(R.id.partFragment, bundle)


//                    var bundle2 = Bundle()
//                    bundle.putSerializable("fruits", modelList)
//                    findNavController().navigate(R.id.navigation_fruit, bundle2)
                }
            })
        rvAdapterTop = CustomAdapterTop(modelList, object : CustomAdapterTop.OnClicListener {
            override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                var bundle = Bundle()
                bundle.putSerializable("models", modelList[position])
                findNavController().navigate(R.id.topFragment, bundle)
            }


        })

        customAdapter= CustomAdapter(fruitList,object :CustomAdapter.OnClicListener{
            override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                var bundle = Bundle()
                bundle.putSerializable("models", fruitList[position])
                findNavController().navigate(R.id.topFragment, bundle)
            }

        })
        customAdapter2= CustomAdapter(vegetableList,object :CustomAdapter.OnClicListener{
            override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                var bundle = Bundle()
                bundle.putSerializable("models", vegetableList[position])
                findNavController().navigate(R.id.topFragment, bundle)
            }

        })
         customAdapter3= CustomAdapter(deviseList,object :CustomAdapter.OnClicListener{
            override fun onItemClic(modelList: ArrayList<Product>, position: Int) {
                var bundle = Bundle()
                bundle.putSerializable("models", deviseList[position])
                findNavController().navigate(R.id.topFragment, bundle)
            }

        })





        Collections.sort(typelList, object : Comparator<ProductType> {
            override fun compare(p0: ProductType?, p1: ProductType?): Int {

                if (p1 != null) {
                    return p0?.type?.compareTo(p1.url.toString())!!
                }
                return 1
            }

        })

        binding.rv.adapter = customAdapter
        binding.rv2.adapter = customAdapter2
        binding.rv3.adapter = customAdapter3
        binding.rvTop.adapter = rvAdapterTop


        binding.apply {

            img1.setOnClickListener {
                val  intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bozor.com/uz/"))
                startActivity(intent)
            }

            rightTop.setOnClickListener {
                var bundle = Bundle()
                bundle.putSerializable("fruits", modelList)
                findNavController().navigate(R.id.navigation_fruit, bundle)
            }


            pieChart.setUsePercentValues(true)
            pieChart.description.isEnabled = false
            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

            pieChart.dragDecelerationFrictionCoef = 0.95f

            pieChart.isDrawHoleEnabled = true
            pieChart.setHoleColor(Color.WHITE)

            pieChart.setTransparentCircleColor(Color.WHITE)
            pieChart.setTransparentCircleAlpha(110)

            pieChart.holeRadius = 58f
            pieChart.transparentCircleRadius = 61f

            pieChart.setDrawCenterText(true)

            pieChart.rotationAngle = 0f

            pieChart.isRotationEnabled = true
            pieChart.isHighlightPerTapEnabled = true

            pieChart.animateY(1400, Easing.EaseInQuad)

            val enties: ArrayList<PieEntry> = ArrayList()
            enties.add(PieEntry(70f))
            enties.add(PieEntry(50f))
            enties.add(PieEntry(30f))

            pieChart.apply {
                legend.isEnabled = false
                setEntryLabelColor(Color.WHITE)
                setEntryLabelTextSize(12f)
            }

            val dataSet = PieDataSet(enties, "Bozor narxlari")
            dataSet.apply {
                setDrawIcons(false)
                sliceSpace = 3f
                iconsOffset = MPPointF(0f, 40f)
                selectionShift = 5f
            }

            val color: ArrayList<Int> = ArrayList()

            color.apply {
                add(resources.getColor(android.R.color.holo_orange_light))
                add(resources.getColor(android.R.color.holo_blue_dark))
                add(resources.getColor(android.R.color.holo_green_light))
            }

            dataSet.colors = color

            val data = PieData(dataSet)
            data.apply {
                setValueFormatter(PercentFormatter())
                setValueTextSize(15f)
                setValueTypeface(Typeface.DEFAULT_BOLD)
                setValueTextColor(Color.WHITE)
            }

            pieChart.data = data
            pieChart.highlightValues(null)
            pieChart.invalidate()


               rightVegetable.setOnClickListener {
                   var bundle = Bundle()
                   bundle.putSerializable("fruits", vegetableList)
                   findNavController().navigate(R.id.myVegetableFragment, bundle)
               }
            rightOther.setOnClickListener {
                var bundle = Bundle()
                bundle.putSerializable("types", typelList)
                bundle.putSerializable("fruits", deviseList)
                findNavController().navigate(R.id.myDeviseFragment, bundle)
            }


               rightFruit.setOnClickListener {
                   var bundle = Bundle()
                   bundle.putSerializable("fruits", fruitList)
                   findNavController().navigate(R.id.myFruitFragment, bundle)
               }




        }


//        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }
        return root
    }

   /* @RequiresApi(Build.VERSION_CODES.R)
    private fun setLineChartData() {

        val linevalues = ArrayList<Entry>()
        linevalues.add(Entry(0f, 0.0F))
        linevalues.add(Entry(1f, 500.0F))
        linevalues.add(Entry(2f, 3000.0F))
        linevalues.add(Entry(3f, 2000.0F))
        linevalues.add(Entry(4f, 1000.0F))
        linevalues.add(Entry(5f, 1000.0F))
        linevalues.add(Entry(6f, 8000.0F))
        linevalues.add(Entry(7f, 10000.0F))
        linevalues.add(Entry(8f, 1000.0F))
        linevalues.add(Entry(9f, 2000.0F))
        linevalues.add(Entry(10f, 5000.0F))
        linevalues.add(Entry(11f, 1000.0F))
        linevalues.add(Entry(12f, 20000.0F))

//linevalues.add(Entry(0f, 0.0F))
//        linevalues.add(Entry(30f, 3000.0F))
//        linevalues.add(Entry(40f, 2000.0F))
//        linevalues.add(Entry(50f, 1000.0F))
//        linevalues.add(Entry(60f, 8000.0F))
//        linevalues.add(Entry(70f, 10000.0F))
//        linevalues.add(Entry(80f, 1000.0F))
//        linevalues.add(Entry(90f, 2000.0F))
//        linevalues.add(Entry(100f, 5000.0F))
//        linevalues.add(Entry(110f, 1000.0F))
//        linevalues.add(Entry(120f, 20000.0F))
//        linevalues.add(Entry(130f, 40000.0F))
//        linevalues.add(Entry(140f, 50000.0F))

        val linedataset = LineDataSet(linevalues, "2022")
        linedataset.color = resources.getColor(R.color.purple_200)
        linedataset.color = Color.rgb(65, 168, 121)
//        val colorList = mutableListOf<Int>()
//        colorList.add(R.color.purple_500)
//        colorList.add(R.color.black)
//        colorList.add(R.color.teal_200)
//        linedataset.setValueTextColors(colorList)

        linedataset.circleRadius = 3f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 10F

//       linedataset.removeFirst()
        linedataset.calcMinMaxY(0F, 200F)
//        linedataset.disableDashedLine()
//        linedataset.disableDashedHighlightLine()
//        linedataset.removeEntry(4)
//        linedataset.fillColor = Color.TRANSPARENT
        linedataset.mode = LineDataSet.Mode.CUBIC_BEZIER


        val xAxisValues: ArrayList<String> = ArrayList(
            listOf(
                "Jan",
                "Feb",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
            )
        )

        binding.getTheGraph.setTouchEnabled(true);
        binding.getTheGraph.isDragEnabled = true;
        binding.getTheGraph.setScaleEnabled(true);
        binding.getTheGraph.setPinchZoom(true);
        binding.getTheGraph.setDrawGridBackground(false);
        binding.getTheGraph.extraLeftOffset = 10f;
//        binding.getTheGraph.extraRightOffset = 10f;
//to hide background lines
        binding.getTheGraph.xAxis.setDrawGridLines(true);
        binding.getTheGraph.axisLeft.setDrawGridLines(true);
        binding.getTheGraph.axisRight.setDrawGridLines(false);

        //to hide right Y and top X border
        //to hide right Y and top X border
        val rightYAxis: YAxis = binding.getTheGraph.axisRight
        rightYAxis.isEnabled = false
        val leftYAxis: YAxis = binding.getTheGraph.axisLeft
        leftYAxis.isEnabled = true
        val topXAxis: XAxis = binding.getTheGraph.xAxis
        topXAxis.isEnabled = true


        val xAxis: XAxis = binding.getTheGraph.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.isEnabled = true
        xAxis.setDrawGridLines(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        linedataset.lineWidth = 2f;
        linedataset.circleRadius = 3f;
        linedataset.setDrawValues(false);
        linedataset.circleHoleColor = resources.getColor(R.color.purple_500);
        linedataset.setCircleColor(resources.getColor(R.color.teal_700));

        binding.getTheGraph.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)


        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        binding.getTheGraph.data = data
        binding.getTheGraph.setBackgroundColor(resources.getColor(R.color.white))
        binding.getTheGraph.animateXY(2000, 2000, Easing.EaseInCubic)

//        binding.getTheGraph.invalidate()
//        binding.getTheGraph.legend.isEnabled = false
        binding.getTheGraph.description.isEnabled = false

    }
*/

    private fun LoadData() {

        modelList = ArrayList()
        typelList = ArrayList()

        fruitList = ArrayList()
        vegetableList = ArrayList()
        polyList = ArrayList()
        foodList = ArrayList()
        deviseList = ArrayList()




        typelList.add(
            ProductType(
                "Mevalar",
                R.drawable.fruits
            )
        )
        typelList.add(
            ProductType(
                "Sabzavotlar",
                R.drawable.vegetable
            )
        )
        typelList.add(
            ProductType(
                "Poliz ekinlari",
                R.drawable.pumpkin
            )
        )
        typelList.add(
            ProductType(
                "Boshqa mahsulotlar",
                R.drawable.drinks
            )
        )
 typelList.add(
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
                1,
                "Kartoshka",
                4000,
                "12.09.2022",
                "Toshkent.sh",
                R.drawable.potato
//                "https://media.istockphoto.com/photos/three-potatoes-picture-id157430678?k=20&m=157430678&s=612x612&w=0&h=dfYLuPYwA50ojI90hZ4jCgKZd5TGnqf24UCVBszoZIA="
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
//                "https://us.123rf.com/450wm/andegro4ka/andegro4ka1504/andegro4ka150400013/39082965-tomato-and-slice-isolated-on-white-photo-realistic-vector-illustration.jpg?ver=6"
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
//                "https://i.pinimg.com/736x/01/7a/d9/017ad925ae84dd2bd373077faca1ec9a.jpg"
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
//                "https://i.pinimg.com/originals/b6/79/54/b67954a7113e1fb688b43eb51477fc3a.jpg"
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
//                "https://thumbs.dreamstime.com/b/carrot-drawing-carrots-isolate-white-background-vector-illustration-193123629.jpg"
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
//                "https://thumbs.dreamstime.com/b/red-apple-24661589.jpg"
//                "https://cdn-user-icons.flaticon.com/64366/64366359/1664342545651.svg"
//            "https://cdn-icons-png.flaticon.com/512/7198/7198462.png"
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
//                "https://media.istockphoto.com/vectors/orange-vector-id165658009?k=20&m=165658009&s=170667a&w=0&h=sMwQQIr9rVL9T765H1ikRqsFBC6lpA-9EAQQVIgCMOQ="
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
//                "https://produits.bienmanger.com/35278-0w470h470_Organic_Galia_Melon.jpg"
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
//                "https://img.freepik.com/premium-vector/ripe-watermelon-juicy-piece-white-background-isolated_269543-89.jpg?w=2000"

            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}