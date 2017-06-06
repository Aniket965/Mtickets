package com.scibots.aniket.mtickets

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import com.squareup.picasso.Picasso


class Detail_screen : AppCompatActivity() {
    var id: Int? = null
    var name: String? = null
    var url_back: String? = null
    var overview: String? = null
    var average_votes: String? = null
    var release_date: String? = null
    val TAG = "Detailed View"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        var intent = intent
        var b = intent.extras
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        if (b != null) {
            id = b.getInt("movieId")
            name = b.getString("moviename")
            url_back = b.getString("moviebackurl")
            overview = b.getString("movieOverview")
            release_date = b.getString("moviedate")
            average_votes = b.getString("movievotes")
        }
//        Log.d(TAG,id?.toString()
        // loading background Image
        var Background = findViewById(R.id.backImageTheme)as ImageView
            Picasso.with(this).load("https://image.tmdb.org/t/p/w780/" + url_back).into(Background)


    }
}
