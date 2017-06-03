package com.scibots.aniket.mtickets

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.TextView


class Home_view : AppCompatActivity() {

    private var TAG = "HOME_VIEW"
    private var titleTextview: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)

        // for making status bar completly transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        titleTextview = findViewById(R.id.genreTitle) as TextView

        //setting font for the title text
        val custom_font = Typeface.createFromAsset(assets, "font/VastShadow-Regular.ttf")
        titleTextview?.typeface = custom_font


    }
}
