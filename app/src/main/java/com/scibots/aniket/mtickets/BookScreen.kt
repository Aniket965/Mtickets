package com.scibots.aniket.mtickets

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BookScreen : AppCompatActivity() {
    var name: String? = null
    var poster_url: String? = null
    var poster: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        var intent = intent
        var b = intent.extras
        if (b != null) {

            name = b.getString("name")
            poster_url = b.getString("url")

        }
        poster = findViewById(R.id.poster) as ImageView
        Picasso.with(this).load("https://image.tmdb.org/t/p/w342/" +poster_url).into(poster)

    }
}
