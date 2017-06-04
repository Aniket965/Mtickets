package com.scibots.aniket.mtickets.adapters

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.scibots.aniket.mtickets.R
import com.scibots.aniket.mtickets.dataclass.Movie

/**
 * Created by aniket on 4/6/17.
 */
class GenreboxAdapter(internal var context: Context, Moviesdataset: ArrayList<Movie>) : RecyclerView.Adapter<GenreboxAdapter.ViewHolder>() {

    var Moviesdataset: ArrayList<Movie>? = null

    init {
        this.Moviesdataset = Moviesdataset
        this.context = context
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card_view, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle: TextView
        var mdisp: TextView
        var img: ImageView
        var mButText: TextView


        init {
            mTitle = itemView.findViewById(R.id.movieTitle) as TextView
            img = itemView.findViewById(R.id.poster) as ImageView
            mdisp = itemView.findViewById(R.id.disp) as TextView
            mButText = itemView.findViewById(R.id.book_button) as TextView
            val custom_font_title = Typeface.createFromAsset(context.assets, "font/OpenSans-CondBold.ttf")
            val custom_font_disp = Typeface.createFromAsset(context.assets, "font/Lora-Italic.ttf")
            mTitle.setTypeface(custom_font_title)
            mdisp.setTypeface(custom_font_disp)
            mButText.setTypeface(custom_font_title)


        }

    }


    }


