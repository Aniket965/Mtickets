package com.scibots.aniket.mtickets


import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
import android.widget.TextView
import com.arlib.floatingsearchview.FloatingSearchView
import com.scibots.aniket.mtickets.SearchSuggestion.movieSuggestion
import com.scibots.aniket.mtickets.adapters.GenreboxAdapter
import com.scibots.aniket.mtickets.dataclass.Movie


class Home_view : AppCompatActivity() {

    private var TAG = "HOME_VIEW"
    private var titleTextview: TextView? = null
    private var mSearchView: FloatingSearchView? = null
    private var mRecylerview: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)

        // for making status bar completly transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        // Recyler view

        // setting Recycler View

        mRecylerview = findViewById(R.id.movieScroll) as RecyclerView?
        mRecylerview?.setHasFixedSize(true)


        //setting Layout Manager
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecylerview?.layoutManager = mLayoutManager

        //setting adapter

        mAdapter = GenreboxAdapter(this, ArrayList<Movie>())
        mRecylerview?.adapter = mAdapter


        titleTextview = findViewById(R.id.genreTitle) as TextView

        //setting font for the title text
        val custom_font = Typeface.createFromAsset(assets, "font/VastShadow-Regular.ttf")
        titleTextview?.typeface = custom_font

        // fake data set now

        var moviesNames: ArrayList<String> = ArrayList();
        moviesNames.add("aniket");
        moviesNames.add("lol");

        // search view
        mSearchView = findViewById(R.id.floating_search_view) as FloatingSearchView
        mSearchView?.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener { oldQuery, newQuery ->

            var mlist: ArrayList<movieSuggestion> = ArrayList();

            for (i in 0..moviesNames.size - 1) {
                if (moviesNames.get(i).contains(newQuery, true)) {
                    mlist.add(movieSuggestion(moviesNames.get(i)))
                }
            }

            mSearchView?.swapSuggestions(mlist)
        })




    }
}
