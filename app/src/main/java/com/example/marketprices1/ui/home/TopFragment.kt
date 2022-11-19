package com.example.marketprices1.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.marketprices1.R
import com.example.marketprices1.databinding.FragmentTopBinding
import com.example.marketprices1.models.Product
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.squareup.picasso.Picasso


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "models"

class TopFragment : Fragment() {

    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getSerializable(ARG_PARAM1) as Product?
        }
    }

    private lateinit var binding: FragmentTopBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopBinding.inflate(layoutInflater)

        (context as AppCompatActivity).supportActionBar!!.title = product?.name

        setLineChartData()

        binding.apply {

            product?.let { Picasso.get().load(it.url).into(img) }
            name.text = product?.name
            price.text = product?.price.toString()
            date.text = product?.priceDate
            place.text = product?.place
            code.text = product?.productCode.toString()

            phone.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + "901782858")
                startActivity(dialIntent)
            }

            sms.setOnClickListener {
//                val uri = String.format("geo:%f,%f", 40, 325874, 76, 1161)
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                startActivity(intent)

//                val uri =
//                    "http://maps.google.com/maps?saddr=" + "9982878" + "," + "76285774" + "&daddr=" + "9992084" + "," + "76286455"
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                intent.setClassName(
//                    "com.google.android.apps.maps",
//                    "com.google.android.maps.MapsActivity"
//                )
//                startActivity(intent)

//                val i = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("geo:37.827500,-122.481670")
//                )
//                startActivity(i)

                val uri = Uri.parse("geo:13.070984,80.253639")
                val `in` = Intent(Intent.ACTION_VIEW, uri)
                startActivity(`in`)


            }



        }
        return binding.root
    }

//    private fun sendSMS(phoneNumber: String, message: String) {
//        val sentPI: PendingIntent = PendingIntent.getBroadcast(requireContext(), 0, Intent("SMS_SENT"), 0)
//        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
//    }

    companion object {
        @JvmStatic
        fun newInstance(product: Product) =
            TopFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, product)
                }
            }
    }


    private fun setLineChartData() {

        val linevalues = ArrayList<Entry>()
        val linevalues1 = ArrayList<Entry>()
        val linevalues2 = ArrayList<Entry>()

        linevalues.add(Entry(0f, 0.0F))
        linevalues.add(Entry(1f, 1.0F))
        linevalues.add(Entry(2f, 3.0F))
        linevalues.add(Entry(3f, 1.0F))
        linevalues.add(Entry(4f, 7.0F))
        linevalues.add(Entry(5f, 0.0F))
        linevalues.add(Entry(6f, 24.0F))
        linevalues.add(Entry(7f, 8.0F))
        linevalues.add(Entry(8f, 32.0F))
        linevalues.add(Entry(9f, 2.0F))
        linevalues.add(Entry(10f, 43.0F))
        linevalues.add(Entry(11f, 6.0F))
        linevalues.add(Entry(12f, 47.0F))



        linevalues1.add(Entry(0f, 3.0F))
        linevalues1.add(Entry(1f, 0.0F))
        linevalues1.add(Entry(2f, 4.0F))
        linevalues1.add(Entry(3f, 2.0F))
        linevalues1.add(Entry(4f, 12.0F))
        linevalues1.add(Entry(5f, 6.0F))
        linevalues1.add(Entry(6f, 20.0F))
        linevalues1.add(Entry(7f, 4.0F))
        linevalues1.add(Entry(8f, 28.0F))
        linevalues1.add(Entry(9f, 3.0F))
        linevalues1.add(Entry(10f, 36.0F))
        linevalues1.add(Entry(11f, 0.0F))
        linevalues1.add(Entry(12f, 44.0F))


        linevalues2.add(Entry(0f, 2.0F))
        linevalues2.add(Entry(1f, 4.0F))
        linevalues2.add(Entry(2f, 0.0F))
        linevalues2.add(Entry(3f, 2.0F))
        linevalues2.add(Entry(4f, 8.0F))
        linevalues2.add(Entry(5f, 2.0F))
        linevalues2.add(Entry(6f, 11.0F))
        linevalues2.add(Entry(7f, 3.0F))
        linevalues2.add(Entry(8f, 30.0F))
        linevalues2.add(Entry(9f, 18.0F))
        linevalues2.add(Entry(10f, 60.0F))
        linevalues2.add(Entry(11f, 75.0F))
        linevalues2.add(Entry(12f, 30.0F))


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

        binding.chart.setDrawGridBackground(false)
        val xAxis: XAxis = binding.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        val linedataset = LineDataSet(linevalues, "2020")
        val linedataset1 = LineDataSet(linevalues1, "2021")
        val linedataset2 = LineDataSet(linevalues2, "2022")
        //We add features to our chart

        linedataset.lineWidth = 2f
        linedataset1.lineWidth = 2f
        linedataset2.lineWidth = 2f
        binding.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
        binding.chart.description.isEnabled = false

        linedataset.setCircleColor(resources.getColor(R.color.purple_200))
        linedataset1.setCircleColor(resources.getColor(R.color.purple_500))
        linedataset2.setCircleColor(resources.getColor(android.R.color.holo_green_light))

        linedataset.circleHoleColor = resources.getColor(R.color.purple_200)
        linedataset1.circleHoleColor = resources.getColor(R.color.purple_500)
        linedataset2.circleHoleColor = resources.getColor(android.R.color.holo_green_light)


        linedataset.color = resources.getColor(com.example.marketprices1.R.color.purple_200)
        linedataset.circleRadius = 2f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 0F
//        linedataset.fillColor = Color.TRANSPARENT
        linedataset.mode = LineDataSet.Mode.CUBIC_BEZIER

        val rightYAxis: YAxis = binding.chart.axisRight
        rightYAxis.isEnabled = false

        linedataset1.color = resources.getColor(com.example.marketprices1.R.color.purple_500)
        linedataset1.circleRadius = 2f
        linedataset1.setDrawFilled(true)
        linedataset1.valueTextSize = 0F
//        linedataset1.fillColor = Color.TRANSPARENT
        linedataset1.mode = LineDataSet.Mode.CUBIC_BEZIER

        linedataset2.color = resources.getColor(android.R.color.holo_green_light)
        linedataset2.circleRadius = 2f
        linedataset2.setDrawFilled(true)
        linedataset2.valueTextSize = 0F
//        linedataset2.fillColor = Color.TRANSPARENT
        linedataset2.mode = LineDataSet.Mode.CUBIC_BEZIER



        binding.chart.data = LineData(linedataset, linedataset1, linedataset2)
        binding.chart.setBackgroundColor(resources.getColor(com.example.marketprices1.R.color.white))
        binding.chart.animateXY(2000, 2000, Easing.EaseInCubic)

    }
}