package com.example.marketprices1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.marketprices1.databinding.ActivitySplashBinding
import com.squareup.picasso.Picasso


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        @Suppress("DEPRECATION")
//        Handler().postDelayed({
//            findNavController().popBackStack()
//            findNavController().navigate(R.id.mainn)
//        },
//            1500
//        )

        binding.apply {
//            Picasso.get().load("https://www.arimetrics.com/wp-content/uploads/2020/01/marketplace-1.png").into(img1)
//            Picasso.get().load("https://miro.medium.com/max/1200/1*Bf0Jt8OIE40taVntJFqhVw.png").into(img1)
            Picasso.get().load("https://img.freepik.com/premium-vector/online-shop-logo-template_567288-422.jpg?w=2000").into(img1)
        }

        Handler().postDelayed({ // This method will be executed once the timer is over
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)

    }
}