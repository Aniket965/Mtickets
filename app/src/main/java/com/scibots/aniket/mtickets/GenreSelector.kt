package com.scibots.aniket.mtickets

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.WindowManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.scibots.aniket.mtickets.adapters.genreAdapter
import org.json.JSONException
import org.json.JSONObject


class GenreSelector : AppCompatActivity() {
    var genre_recycler_view: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    var genreDataset: ArrayList<HashMap<Int, String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_selector)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        genreDataset = ArrayList()
        getGenrelist()
        genre_recycler_view = findViewById(R.id.genreRec) as RecyclerView
        genre_recycler_view?.setHasFixedSize(true)


        //setting Layout Manager
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        genre_recycler_view?.layoutManager = mLayoutManager

        //setting adapter
        mAdapter = genreAdapter(this, genreDataset!!)
        genre_recycler_view?.adapter = mAdapter

    }

    fun getGenrelist(): ArrayList<HashMap<String, String>>? {

        var RequesQue: RequestQueue = Volley.newRequestQueue(this)

        // url for genre
        var url = "https://api.themoviedb.org/3/genre/movie/list?api_key=f42a418b0aba174156496701672bebf7&language=en-US"

        var jsonRequest = JsonObjectRequest(Request.Method.GET, url, null,
                object : Response.Listener<JSONObject> {


                    override fun onResponse(p0: JSONObject?) {

                        parseGenrejsonIntoArraylist(p0)
                        Log.d("HOME", genreDataset?.toString())
                    }

                }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError) {

                Log.e("HOMESCREEN", "genre Request didnot work! ")
            }
        })

        RequesQue.add(jsonRequest)
        return null

    }

    fun parseGenrejsonIntoArraylist(response: JSONObject?) {

        if (response != null) {
            try {


                var Genres = response.getJSONArray("genres")

                for (i in 0..Genres?.length()!! - 1) {
                    Log.d("HOME", i.toString())
                    var genreobj = Genres.getJSONObject(i)
                    var Gid = genreobj.get("id")
                    var Gname = genreobj.get("name")
                    var genre = HashMap<Int, String>()
                    genre.put(Gid as Int, Gname as String)
                    genreDataset?.add(genre)
                }
                mAdapter?.notifyDataSetChanged()

            } catch (exception: JSONException) {
                Log.e("HOMESCREEN", "genre Request didnot work! ")
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed();
        return
    }


}
