package com.scibots.aniket.mtickets


import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
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
    var doubleBackToExitPressedOnce = false
    var emptyView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)
        var intent = intent
        var b = intent.extras
        //id for action
        var id: Int? = 28
        var name: String? = "Action"
        if (b != null) {
            id = b.getInt("genreId")
            name = b.getString("genreName")
        }

        emptyView = findViewById(R.id.empty_view) as TextView



        if (name != null) {
            genreTextview?.setText(name)
        } else {
            genreTextview?.setText(getString(R.string.defaultgenre))
        }
        MovieList = ArrayList<Movie>();
        // for making status bar completly transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        if (id != 0) {

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
        if (MovieList?.isEmpty()!!) {
            mRecylerview?.setVisibility(View.GONE);
            emptyView?.setVisibility(View.VISIBLE);
        }


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

    }


    private fun getmoviesfromgenre(id: Int?): ArrayList<Movie>? {
        var dataset = ArrayList<Movie>()


        val url = "https://api.themoviedb.org/3/genre/$id/movies?api_key=f42a418b0aba174156496701672bebf7&language=en-US&include_adult=false&sort_by=created_at.asc"
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null,
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
                        movie.getString("vote_average"),
                        movie.getString("original_language"),
                        movie.getString("poster_path")
                ));
                Log.d(TAG, "done" + MovieList?.size);


//                Searchview Fake Dataset
                mSearchView = findViewById(R.id.floating_search_view) as FloatingSearchView
                mSearchView?.setOnQueryChangeListener(FloatingSearchView.OnQueryChangeListener { oldQuery, newQuery ->

                    val mlist: ArrayList<movieSuggestion> = ArrayList();

                    for (i in 0..MovieList?.size!! - 1) {

                        val movie: Movie = MovieList?.get(i)!!

                        if (movie.original_title.contains(newQuery, true)) {
                            mlist.add(movieSuggestion(movie.original_title))
                        }
                        /*
                        * Opens movie details view if query happend
                        * */
                        if (movie.original_title.equals(newQuery)) {
                            var intent: Intent = Intent(this, Detail_screen::class.java)
                            var b = Bundle()
                            b.putInt("movieId", movie.id)
                            b.putString("moviename", movie.original_title)
                            b.putString("moviebackurl", movie.backdrop_path)
                            b.putString("movieOverview", movie.overview)
                            b.putString("moviedate", movie.release_date)
                            b.putString("movievotes", movie.vote_average)
                            b.putString("lang", movie.language)
                            b.putString("poster_url", movie.poster_url)
                            intent.putExtras(b)
                            this.startActivity(intent)

                        }

                    }

                    mSearchView?.swapSuggestions(mlist)
                })




            }
            mSearchView?.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
                override fun onSearchAction(currentQuery: String?) {

                }

                override fun onSuggestionClicked(searchSuggestion: SearchSuggestion) {
                    for (i in 0..MovieList?.size!! - 1) {
                        val movie: Movie = MovieList?.get(i)!!

                        if (movie.original_title.equals(searchSuggestion.body)) {

                            opendetailPage(movie)
                        }

                    }
                }
            })




            mRecylerview?.setVisibility(View.VISIBLE);
            emptyView?.setVisibility(View.GONE);
            mAdapter?.notifyDataSetChanged()

        } catch (e: JSONException) {

        }

        return MovieList
    }

    private fun opendetailPage(movie: Movie) {
        var intent: Intent = Intent(this, Detail_screen::class.java)
        var b = Bundle()
        b.putInt("movieId", movie.id)
        b.putString("moviename", movie.original_title)
        b.putString("moviebackurl", movie.backdrop_path)
        b.putString("movieOverview", movie.overview)
        b.putString("moviedate", movie.release_date)
        b.putString("movievotes", movie.vote_average)
        b.putString("lang", movie.language)
        b.putString("poster_url", movie.poster_url)
        intent.putExtras(b)
        this.startActivity(intent)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
