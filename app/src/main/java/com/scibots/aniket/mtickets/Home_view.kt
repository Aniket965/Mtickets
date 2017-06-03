package com.scibots.aniket.mtickets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.arlib.floatingsearchview.FloatingSearchView

class Home_view : AppCompatActivity() {
    private var mSearchView: FloatingSearchView? = null
    private var TAG = "HOME_VIEW"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)

        // Search view
        mSearchView = findViewById(R.id.floating_search_view) as FloatingSearchView
        mSearchView?.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener { oldQuery, newQuery ->
            //get suggestions based on newQuery
            Log.d(TAG, newQuery);
            //pass them on to the search view

        });

    }
}
