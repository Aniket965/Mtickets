package com.scibots.aniket.mtickets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


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

        if (b != null) {
            id = b.getInt("movieId")
            name = b.getString("moviename")
            url_back = b.getString("moviebackurl")
            overview = b.getString("movieOverview")
            release_date = b.getString("moviedate")
            average_votes = b.getString("movievotes")
        }
        Log.d(TAG,id?.toString())
    }
}
