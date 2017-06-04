package com.scibots.aniket.mtickets


import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.arlib.floatingsearchview.FloatingSearchView
import com.scibots.aniket.mtickets.Que.MySingleton
import com.scibots.aniket.mtickets.SearchSuggestion.movieSuggestion
import com.scibots.aniket.mtickets.adapters.GenreboxAdapter
import com.scibots.aniket.mtickets.dataclass.Movie
import org.json.JSONException
import org.json.JSONObject


class Home_view : AppCompatActivity() {

    private var TAG = "HOME_VIEW"
    private var titleTextview: TextView? = null
    private var genreTextview: TextView? = null
    private var mSearchView: FloatingSearchView? = null
    private var mRecylerview: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var MovieList: ArrayList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)
        var intent = intent
        var b = intent.extras
        //id for action
        var id: Int? = 28
        var name = "Action"
        if (b != null) {

            id = b.getInt("genreId")
            name = b.getString("genreName")
        }
        genreTextview = findViewById(R.id.genreTitle) as TextView
        genreTextview?.setText(name)
        MovieList = ArrayList<Movie>();
        // for making status bar completly transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        if (id != null) {
            getmoviesfromgenre(id);
        } else {
            getmoviesfromgenre(28);
        }
        // Recyler view

        // setting Recycler View

        mRecylerview = findViewById(R.id.movieScroll) as RecyclerView?
        mRecylerview?.setHasFixedSize(true)


        //setting Layout Manager
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecylerview?.layoutManager = mLayoutManager

        //setting adapter

        mAdapter = GenreboxAdapter(this, MovieList)
        mRecylerview?.adapter = mAdapter




        titleTextview = findViewById(R.id.genreTitle) as TextView

        //setting font for the title text
        val custom_font = Typeface.createFromAsset(assets, "font/OpenSans-CondBold.ttf")
        titleTextview?.typeface = custom_font


    }
    /*
    * Function for opeing genreselector activity
    * */

    public fun open_genre_list(view: View) {

        var intent: Intent = Intent(this, GenreSelector::class.java)
        startActivity(intent)
        finish()

    }


    private fun getmoviesfromgenre(id: Int?): ArrayList<Movie>? {
        var dataset = ArrayList<Movie>()


        val url = "https://api.themoviedb.org/3/genre/$id/movies?api_key=f42a418b0aba174156496701672bebf7&language=en-US&include_adult=false&sort_by=created_at.asc"
        var jsonRequest = JsonObjectRequest(Request.Method.GET, url, null,
                object : Response.Listener<JSONObject> {
                    override fun onResponse(p0: JSONObject?) {
                        parsemovies(p0)

                    }

                }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError) {

                Log.e("HOMESCREEN", "genre Request didnot work! ")
            }
        })
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
        return dataset
    }

    private fun parsemovies(response: JSONObject?): ArrayList<Movie>? {



        try {

            // Getting JSON Array node
            val movies = response!!.getJSONArray("results")


            // looping through All Contacts
            for (i in 0..movies.length() - 1) {

                val movie = movies.getJSONObject(i)

                MovieList?.add(Movie(movie.getInt("id"),
                        movie.getString("original_title"),
                        movie.getString("overview"),
                        movie.getString("backdrop_path"),
                        movie.getString("release_date"),
                        movie.getString("vote_average")
                ));
                Log.d(TAG, "done" + MovieList?.size);


//                Searchview Fake Dataset
                mSearchView = findViewById(R.id.floating_search_view) as FloatingSearchView
                mSearchView?.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener { oldQuery, newQuery ->

                    var mlist: ArrayList<movieSuggestion> = ArrayList();

                    for (i in 0..MovieList?.size!! - 1) {

                        var movie: Movie = MovieList?.get(i)!!

                        if (movie.original_title.contains(newQuery, true)) {
                            mlist.add(movieSuggestion(movie.original_title))
                        }
                    }

                    mSearchView?.swapSuggestions(mlist)
                })



            }




            mAdapter?.notifyDataSetChanged()

        } catch (e: JSONException) {

        }

        return MovieList
    }
}
