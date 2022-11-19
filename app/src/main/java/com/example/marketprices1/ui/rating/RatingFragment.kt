package com.example.marketprices1.ui.rating

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.marketprices1.R
import com.example.marketprices1.databinding.FragmentRatingBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RatingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentRatingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRatingBinding.inflate(layoutInflater)

        (context as AppCompatActivity).supportActionBar?.title = "Ko'rsatkich"
        Picasso.get().load("https://image.shutterstock.com/image-vector/global-market-logo-600w-580253467.jpg").into(binding.img1)

//        setLineChartData()

        binding.apply {
                img1.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bozor.com/uz/"))
                    startActivity(intent)
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
//            enties.add(PieEntry(10f))

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
                    add(resources.getColor(R.color.purple_200))
                    add(resources.getColor(R.color.purple_500))
                    add(resources.getColor(R.color.teal_200))
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


            }


            return binding.root
        }


        /*  private fun setLineChartData() {

              val linevalues = ArrayList<Entry>()
              linevalues.add(Entry(0f, 0.0F))
              linevalues.add(Entry(1f, 4.0F))
              linevalues.add(Entry(2f, 0.0F))
              linevalues.add(Entry(3f, 3.0F))
              linevalues.add(Entry(4f, 2.0F))
              linevalues.add(Entry(5f, 1.0F))
              linevalues.add(Entry(6f, 8.0F))
              linevalues.add(Entry(7f, 10.0F))
              linevalues.add(Entry(8f, 1.0F))
              linevalues.add(Entry(9f, 2.0F))
              linevalues.add(Entry(10f, 5.0F))
              linevalues.add(Entry(11f, 1.0F))
              linevalues.add(Entry(12f, 20.0F))
      //        linevalues.add(Entry(13f, 40.0F))
      //        linevalues.add(Entry(14f, 50.0F))

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

              val linedataset = LineDataSet(linevalues, "Uz.Stat")
              //We add features to our chart
              val xAxis: XAxis = binding.getTheGraph.xAxis
              xAxis.position = XAxis.XAxisPosition.BOTTOM

              linedataset.color = resources.getColor(R.color.purple_200)
              linedataset.circleRadius = 3f
              linedataset.circleHoleColor = resources.getColor(R.color.purple_200);
              linedataset.setCircleColor(resources.getColor(R.color.purple_200));
              linedataset.setDrawFilled(true)
              linedataset.valueTextSize = 10F
      //        linedataset.fillColor = Color.GREEN
              linedataset.mode = LineDataSet.Mode.CUBIC_BEZIER;

              binding.getTheGraph.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
              binding.getTheGraph.description.isEnabled = false

              val rightYAxis: YAxis = binding.getTheGraph.axisRight
              rightYAxis.isEnabled = false
             val leftYAxis: YAxis = binding.getTheGraph.axisLeft
              leftYAxis.isEnabled = true
              val topXAxis: XAxis = binding.getTheGraph.xAxis
              topXAxis.isEnabled = true

              linedataset.lineWidth = 2f
              linedataset.setDrawFilled(true)
              //We connect our data to the UI Screen
              val data = LineData(linedataset)
              binding.getTheGraph.data = data
              binding.getTheGraph.setBackgroundColor(resources.getColor(R.color.white))
              binding.getTheGraph.animateXY(2000, 2000, Easing.EaseInCubic)

          }*/
        companion object {
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                RatingFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }