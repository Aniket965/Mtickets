package com.scibots.aniket.mtickets.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.scibots.aniket.mtickets.Detail_screen
import com.scibots.aniket.mtickets.Home_view
import com.scibots.aniket.mtickets.R
import com.scibots.aniket.mtickets.dataclass.Movie
import com.squareup.picasso.Picasso

/**
 * Created by aniket on 4/6/17.
 */
class GenreboxAdapter(internal var context: Context, Moviesdataset: ArrayList<Movie>?) : RecyclerView.Adapter<GenreboxAdapter.ViewHolder>() {

    var Moviesdataset: ArrayList<Movie>? = null

    init {
        this.Moviesdataset = Moviesdataset
        this.context = context
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            var url: String
            var movie: Movie = Moviesdataset?.get(i)!!
            url = movie.backdrop_path
            viewHolder.mTitle.setText(movie.original_title)
            viewHolder.mdisp.setText(movie.overview)
            viewHolder.setImage(url);

        viewHolder.mButText.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                        var intent: Intent = Intent(context, Detail_screen::class.java)
                        var b = Bundle()
                        b.putInt("movieId", movie.id)
                        b.putString("moviename", movie.original_title)
                        b.putString("moviebackurl", movie.backdrop_path)
                        b.putString("movieOverview", movie.overview)
                        b.putString("moviedate", movie.release_date)
                        b.putString("movievotes", movie.vote_average)
                        intent.putExtras(b)
                        context.startActivity(intent)
                        (context as Activity).finish()

            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card_view, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        if (Moviesdataset != null) {
            return Moviesdataset?.size!!
        } else {
            return 0
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle: TextView
        var mdisp: TextView
        var img: ImageView
        var mButText: TextView = itemView.findViewById(R.id.book_button) as TextView


        init {
            mTitle = itemView.findViewById(R.id.movieTitle) as TextView
            img = itemView.findViewById(R.id.poster) as ImageView
            mdisp = itemView.findViewById(R.id.disp) as TextView

            val custom_font_title = Typeface.createFromAsset(context.assets, "font/OpenSans-CondBold.ttf")
            val custom_font_disp = Typeface.createFromAsset(context.assets, "font/Lora-Italic.ttf")

            mTitle.setTypeface(custom_font_title)
            mdisp.setTypeface(custom_font_disp)
            mButText.setTypeface(custom_font_title)


        }

        fun setImage(url: String?) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + url).into(img)
        }

    }


    }


